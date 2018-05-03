package com.concurrency.fundamental.threadSafe;

import java.awt.*;

/**
 * @author xwiam
 * @create 2018/05/03 19:07
 * @desc
 **/
public class SafeListener {

    private final EventListener eventListener;

    private String name = null;

    private SafeListener() {
        eventListener = new EventListener() {
            @Override
            public void onEvent(Event event) {
                doSomeThing(event);
            }
        };
        name = "test";
    }

    void doSomeThing(Event event) {
        System.out.println(name.toString());
    }

    public static SafeListener getInstance(EventSource eventSource) {
        SafeListener safeListener = new SafeListener();
        eventSource.registerListener(safeListener.eventListener);
        return safeListener;
    }

    public static void main(String[] args) {
        EventSource eventSource = new SourceInstance();
        getInstance(eventSource);
    }

}
