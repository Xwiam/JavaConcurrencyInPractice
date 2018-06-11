package com.concurrency.fundamental.cancellatonandshutDown;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xwiam
 * @create 2018/06/11 18:20
 * @desc executorService that keeps track of cancelled tasks after shutdown
 **/
public class TrackingExecutor extends AbstractExecutorService {

    private final ExecutorService exec;
    private final Set<Runnable> taskCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    @Override
    public void shutdown() {
        exec.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }

    public List<Runnable> getCancellableTasks() {
        if (!exec.isTerminated()) {
            throw new IllegalStateException("");
        }
        return new ArrayList<Runnable>(taskCancelledAtShutdown);
    }

    @Override
    public void execute(final Runnable runnable) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    runnable.run();
                }finally {
                    if (isShutdown() && Thread.currentThread().isInterrupted()) {
                        taskCancelledAtShutdown.add(runnable);
                    }
                }
            }
        });

    }
}
