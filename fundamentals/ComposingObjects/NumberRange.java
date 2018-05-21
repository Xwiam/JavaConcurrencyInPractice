package com.concurrency.fundamental.composingobjects;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xwiam
 * @create 2018/05/20 15:37
 * @desc
 **/

@NotThreadSafe
public class NumberRange {

    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i < upper.get() && i > lower.get());
    }

}
