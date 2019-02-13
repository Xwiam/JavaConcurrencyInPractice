package com.concurrency.fundamental.avoidlivenesshazards;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xiwam
 * @Date 2019/2/13 23:57
 * @Desc Using Open Calls to Avoiding Deadlock Between Cooperating Objects.
 */
public class Taxi {

    private Position location,destination;
    private Dispatcher dispatcher;

    public synchronized Position  getLocation() {
        return location;
    }


    //first acquire Taxi lock
    //then acquire Dispatcher lock
    //it will introduce the possibility of deadlock when another thread
    // run the method getImage1() at the same time.
    public synchronized void setLocation1(Position location) {
        this.location = location;
        if (location.equals(destination)) {
            dispatcher.notifyAllAvaliable(this);
        }
    }

    //shrinking the synchronized blocks to guard only operations that involve shared state
    public void setLocation2(Position location) {
        boolean reachedDestination;
        synchronized (this) {
            this.location = location;
            reachedDestination = location.equals(destination);
        }
        if (reachedDestination) {
            dispatcher.notifyAllAvaliable(this);
        }
    }
}

class Dispatcher{
    private Set<Taxi> taxis;

    private Set<Taxi> avaliableTaxi = new HashSet<Taxi>();
    public synchronized void notifyAllAvaliable(Taxi taxi) {
        avaliableTaxi.add(taxi);
    }

    //first acquire Dispatcher lock
    //then acquire Taxi lock
    public synchronized Image getImage1() {
        Image image = new Image();
        for (Taxi t : taxis) {
            image.drawMarker(t.getLocation());
        }
        return image;
    }

    //shrinking the synchronized blocks to guard only operations that involve shared state
    public Image getImage2() {
        Set<Taxi> copy;
        synchronized (this) {
            copy = new HashSet<Taxi>(taxis);
        }
        Image image = new Image();
        for (Taxi t : copy) {
            image.drawMarker(t.getLocation());
        }
        return image;
    }

}

class Position{

}
class Image{
    public void drawMarker(Position location) {
    }
}
