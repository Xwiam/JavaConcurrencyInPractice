package com.concurrency.fundamental.TaskExecution;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author xwiam
 * @create 2018/06/08 12:58
 * @desc using CompletionService to Render Page Elements as they become avaliable
 **/
public abstract class Render {

    private final ExecutorService executor;

    Render(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(final CharSequence source) {
        final List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);
        for (final ImageInfo imageInfo : info) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }

        renderText(source);

        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }

    interface ImageData{

    }
    interface ImageInfo{
        ImageData downloadImage();
    }


    abstract void renderText(CharSequence sequence);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData imageData);
}
