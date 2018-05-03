package com.concurrency.fundamental.threadSafe;

import java.awt.*;

/**
 * @author xwiam
 * @create 2018/05/03 18:15
 * @desc
 **/
public class ThisEscape {

    private String name = null;

    private ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                doSomething(event);
            }
        });
        name = "test";
    }

    void doSomething(Event e) {
        System.out.println(name.toString());
    }

    public static void main(String[] args) {
        EventSource eventSource = new SourceInstance();
        new ThisEscape(eventSource);
    }
}
