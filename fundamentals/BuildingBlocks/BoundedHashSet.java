package com.concurrency.fundamental.buildingblocks;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * @author xwiam
 * @create 2018/05/27 17:37
 * @desc using semaphore to bound a collection
 **/
public class BoundedHashSet<T> {

    private final Set<T> set;

    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException{
        sem.acquire();
        boolean wasAdded = false;
        try{
            wasAdded = set.add(t);
            return wasAdded;
        }finally {
            if (!wasAdded) {
                sem.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            sem.release();
        }
        return wasRemoved;
    }
}