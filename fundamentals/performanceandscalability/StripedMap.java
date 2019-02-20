package com.concurrency.fundamental.performanceandscalability;

/**
 * @author xiwam
 * @Date 2019/2/20 2:15
 * @Desc
 */
public class StripedMap {
    private static final int N_LOCK = 16;
    private final Node[] buckets;
    private final Object[] lock;

    private static class Node{
        Object key;
        Object value;
        Node next;
        public Node next() {
            return next;
        }
    }

    public StripedMap(int num) {
        buckets = new Node[num];
        lock = new Object[N_LOCK];
        for (int i = 0; i < N_LOCK; i++) {
            lock[i] = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public Object get(Object key) {
        int hash = hash(key);
        synchronized (lock[hash % N_LOCK]) {
            for (Node m = buckets[hash]; m != null; m = m.next()) {
                if (m.key.equals(key)) {
                    return m.value;
                }
            }
        }
        return null;
    }

    public void clear() {

        for (int i = 0;i < buckets.length;i++) {
            synchronized (lock[i % N_LOCK]) {
                buckets[i] = null;
            }
        }

    }
}
