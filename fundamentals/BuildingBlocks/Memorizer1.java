package com.concurrency.fundamental.buildingblocks;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xwiam
 * @create 2018/05/27 17:48
 * @desc initial Cache attempt using HashMap and Synchronization
 **/
public class Memorizer1<A, V> implements Computable<A, V>{

    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<A, V>();

    private final Computable<A, V> c;

    public Memorizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
