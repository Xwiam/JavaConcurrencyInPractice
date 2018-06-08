package com.concurrency.fundamental.TaskExecution;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xwiam
 * @create 2018/06/08 12:48
 * @desc Waiting for image download with Future
 **/
public abstract class FutureRender {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<ImageData>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };

        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);

        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        } catch (InterruptedException e) {
            //Re-assert the thread's interrupted status
            Thread.currentThread().interrupt();
            //We don't need the result,so cancel the task too.
            future.cancel(true);
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
