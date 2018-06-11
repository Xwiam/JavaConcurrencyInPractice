package com.concurrency.fundamental.cancellatonandshutDown;

import java.util.concurrent.*;

/**
 * @author xwiam
 * @create 2018/06/11 17:17
 * @desc Cancelling a task using future
 **/
public class TimeRun {

    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timeRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException{
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            //task will be cancelled below
        } catch (ExecutionException e) {
            //exception thrown in task;rethrow
            throw LaunderThrowable.launderThrowable(e.getCause());
        }finally {
            //Harmless if task already completed.
            task.cancel(true); //interrupt if running
        }
    }
}
