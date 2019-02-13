package com.concurrency.fundamental.applyingthreadpools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * @author xiwam
 * @Date 2019/2/13 9:51
 * @Desc To measure a task's runtime,beforeExecute must record the start time and store it somewhere
 * afterExecute can find it.Because execution hooks are called in the thread that executes the task,
 * a value placed in a ThreadLocal by beforeExecute can be retrieved by afterExecute.TimingThreadPool
 * uses a pair of AtomicLongs to keep track of the total number of tasks processed and the total processing
 * time,and uses the terminated hook to print a log message showing the average task time.
 */
public class TimingThreadPool extends ThreadPoolExecutor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private final Logger log = Logger.getLogger("TimingThreadPool");
    private final AtomicLong numTask = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();
    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.fine(String.format("Thread %s:start %s", t, r));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long current = System.nanoTime();
            long endTime = current - startTime.get();
            numTask.incrementAndGet();
            totalTime.addAndGet(endTime);
            log.info(String.format("Thread %s: end %s,time=%dns", t, r, endTime));
        }finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try{
            log.info(String.format("Terminated: avg time=%dns", totalTime.get() / numTask.get()));
        }finally {
            super.terminated();
        }
    }
}
