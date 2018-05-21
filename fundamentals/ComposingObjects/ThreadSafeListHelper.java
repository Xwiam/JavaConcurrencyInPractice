package com.concurrency.fundamental.composingobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xwiam
 * @create 2018/05/20 15:48
 * @desc implenting put-if-absent with Client-side locking.
 **/
@ThreadSafe
public class ThreadSafeListHelper<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
