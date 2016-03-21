package com.reisal78.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Astanid on 21.03.2016.
 */

public abstract class AbstractServer implements Subject, Runnable {

    protected List<Observer> observers = new ArrayList<>();

    @Override
    public void registryObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void deleteObserver(Observer o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }

    public abstract void stop();


}
