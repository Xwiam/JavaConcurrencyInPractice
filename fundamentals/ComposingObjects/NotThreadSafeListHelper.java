package com.concurrency.fundamental.composingobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xwiam
 * @create 2018/05/20 15:45
 * @desc
 **/
@NotThreadSafe
public class NotThreadSafeListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}
