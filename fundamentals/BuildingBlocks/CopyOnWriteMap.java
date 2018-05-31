package com.concurrency.fundamental.buildingblocks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author xwiam
 * @create 2018/05/31 16:55
 * @desc
 **/
public class CopyOnWriteMap<K, V>  implements Map<K, V>, Cloneable {

    private volatile Map<K, V> internalMap;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return internalMap.get(key);
    }

    @Override
    public V put(K key, V value) {

        synchronized (this) {
            Map<K, V> map = new HashMap<K, V>(internalMap);
            map.put(key, value);
            internalMap = map;
            return value;
        }
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        synchronized (this) {
            Map<K, V> map = new HashMap<K, V>(internalMap);
            map.putAll(m);
            internalMap = map;
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
