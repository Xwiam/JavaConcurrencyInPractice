package com.concurrency.fundamental.applyingthreadpools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author xiwam
 * @Date 2019/2/12 9:55
 * @Desc
 */
public class ThreadDeadLock {

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public class TestTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> foot = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "foot";
                }
            });
            String test = "test";
            return test +  foot.get();
        }
    }

    public void run() {
        try {
            new TestTask().call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadDeadLock threadDeadLock = new ThreadDeadLock();
        threadDeadLock.run();

    }
}
