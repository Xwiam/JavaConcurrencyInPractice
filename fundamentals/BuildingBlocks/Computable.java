package com.concurrency.fundamental.buildingblocks;

/**
 * @author xwiam
 * @create 2018/05/27 17:46
 * @desc
 **/
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
