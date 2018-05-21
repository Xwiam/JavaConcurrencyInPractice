package com.concurrency.fundamental.composingobjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xwiam
 * @create 2018/05/11 18:12
 * @desc
 **/
public class MonitorVehicleTracker {

    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized void setLocations(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID : " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();

        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }

        return Collections.unmodifiableMap(result);
    }

    public static void main(String[] args) {
        Map<String, MutablePoint> mutablePointMap = new HashMap<String, MutablePoint>();
        MutablePoint mutablePoint = new MutablePoint();
        mutablePointMap.put("1", mutablePoint);
        MonitorVehicleTracker monitorVehicleTracker = new MonitorVehicleTracker(mutablePointMap);
        Map<String, MutablePoint> mutablePointMap1 = monitorVehicleTracker.getLocations();
        for (String key : mutablePointMap1.keySet()) {
            System.out.println(" key : " + key + "==" + "value : " + mutablePointMap1.get(key).x);
        }
        monitorVehicleTracker.setLocations("1", 1, 1);
        System.out.println(monitorVehicleTracker.getLocations().get("1").y);

    }
}
