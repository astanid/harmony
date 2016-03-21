package com.reisal78.app;

/**
 * Created by Astanid on 21.03.2016.
 */
public interface Subject {
    void registryObserver(Observer o);

    void deleteObserver(Observer o);

    void notifyAllObservers();
}
