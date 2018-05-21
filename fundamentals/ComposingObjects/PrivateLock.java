package com.concurrency.fundamental.composingobjects;

/**
 * @author xwiam
 * @create 2018/05/20 15:19
 * @desc
 **/
public class PrivateLock {

    private final Object myLock = new Object();
    @GuardedBy("mylock") Widget widget;

    void someMethod() {
        synchronized (myLock) {
            //Access or modify the state of widget
            widget.setHeight(1);
        }
    }
}
