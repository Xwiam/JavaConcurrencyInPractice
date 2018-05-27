package com.concurrency.fundamental.buildingblocks;

/**
 * @author xwiam
 * @create 2018/05/27 17:29
 * @desc StaticUtilities
 **/
public class LaunderThrowable {

    public static RuntimeException launderThrowable(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        } else if (throwable instanceof Error) {
            throw (Error) throwable;
        }else {
            throw new IllegalStateException("Not unchecked" + throwable);
        }

    }
}
