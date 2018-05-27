package com.concurrency.fundamental.buildingblocks;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author xwiam
 * @create 2018/05/27 16:58
 * @desc Iteration Hidden within String Concatenation.Don't do this because this indirect
 *        use of iteration can cause ConcurrentModificationException.
 **/
public class HiddenIterator {

    @GuardedBy("this")
    private final Set<Integer> set = new HashSet<Integer>();

    public synchronized void add(Integer i) {
        set.add(i);
    }

    public synchronized void remove(Integer integer) {
        set.remove(integer);
    }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0;i < 10;i++) {
            add(r.nextInt());
        }
        System.out.println("DEBUG: added ten elements to " + set);
    }
}
