package com.concurrency.fundamental.applyingthreadpools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiwam
 * @Date 2018/12/12 16:38
 * @Desc
 */
public class SaturationPolicy {

    private final static int N_THREAD = 1;
    private final static int N_QUEUE = 1;

    private ThreadPoolExecutor executor;

    public SaturationPolicy() {
        executor = new ThreadPoolExecutor(N_THREAD, N_THREAD, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(N_QUEUE));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void main(String[] args) {
        SaturationPolicy saturationPolicy = new SaturationPolicy();
        saturationPolicy.execute();
    }

    public void execute() {
        try {
            for (int i = 0;i < 10;i++) {
                executor.execute(new Worker(i));
            }
        } catch (RejectedExecutionException e) {
            executor.shutdown();
        }finally {
            executor.shutdown();
        }
    }
}

class Worker implements Runnable {

    private int i;

    public Worker(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(" task :" + i + " is running : " + Thread.currentThread().getName());
    }
}
