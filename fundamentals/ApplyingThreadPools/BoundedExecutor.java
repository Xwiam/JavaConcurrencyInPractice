package com.concurrency.fundamental.applyingthreadpools;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @author xiwam
 * @Date 2018/12/11 23:07
 * @Desc using a semaphore to throttle task submission
 */
@ThreadSafe
public class BoundedExecutor {

    //bounded executor,unbounded queue
    private final Executor executor;
    //semaphore is used to bound the task injection rate.
    //semaphore is equal to the pool size plus the number of queued tasks you want to allow,
    //since the semaphore is bounding the number of tasks both currently executing and awaiting execution.
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, int bound) {
        this.executor = exec;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.run();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}
