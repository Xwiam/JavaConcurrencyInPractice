package com.concurrency.fundamental.buildingblocks;

import java.util.concurrent.CountDownLatch;

/**
 * @author xwiam
 * @create 2018/05/27 17:15
 * @desc using CountLatch for starting and stopping threads in timing tests
 **/
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0;i < nThreads;i++) {
            Thread t = new Thread(){
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException ignored){
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
