package com.concurrency.fundamental.threadSafe;

/**
 * @author xwiam
 * @create 2018/05/03 18:15
 * @desc
 **/
public interface EventSource {

    void registerListener(EventListener eventListener);
}
