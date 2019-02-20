package com.concurrency.fundamental.performanceandscalability;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author xiwam
 * @Date 2019/2/20 1:33
 * @Desc
 */
public class AttributeStore {

    private final Map<String, String> attrMap = new HashMap<String, String>();

    public synchronized boolean userLocationMatches(String name,String regexp) {
        String key = "users." + name + ".location";
        String location = attrMap.get(key);
        if (location == null) {
            return false;
        } else {
            return Pattern.matches(regexp, location);
        }
    }

    //constructing the key string and processing the regular expression do not access shared state.
    //they need not executed with the lock field.
    public boolean betterUserLocationMatches(String name, String regexp) {
        String key = "users." + name + ".location";
        String location;
        synchronized (this) {
            location = attrMap.get(key);
        }
        if (location == null) {
            return false;
        } else {
            return Pattern.matches(regexp, location);
        }
    }
}
