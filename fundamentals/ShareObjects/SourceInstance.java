package com.concurrency.fundamental.threadSafe;

/**
 * @author xwiam
 * @create 2018/05/03 18:21
 * @desc
 **/
public class SourceInstance implements EventSource {

    @Override
    public void registerListener(EventListener eventListener) {
        eventListener.onEvent(null);
    }
}
