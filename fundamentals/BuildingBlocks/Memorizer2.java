package com.concurrency.fundamental.buildingblocks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xwiam
 * @create 2018/05/27 17:52
 * @desc Replacing HashMap with ConcurrentHashMap
 **/
public class Memorizer2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();

    private final Computable<A, V> c;

    public Memorizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;

    }
}
