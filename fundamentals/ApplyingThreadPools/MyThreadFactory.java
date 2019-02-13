package com.concurrency.fundamental.applyingthreadpools;

import java.util.concurrent.ThreadFactory;

/**
 * @author xiwam
 * @Date 2019/2/12 12:50
 * @Desc
 */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String name) {
        poolName = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
