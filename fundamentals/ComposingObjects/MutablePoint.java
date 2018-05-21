package com.concurrency.fundamental.composingobjects;

import com.concurrency.fundamental.threadSafe.NotThreadSafe;

/**
 * @author xwiam
 * @create 2018/05/11 18:10
 * @desc
 **/

@NotThreadSafe
public class MutablePoint {

    public int x,y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint point) {
        this.x = point.x;

        this.y = point.y;
    }
}
