package com.reisal78.app;

/**
 * Created by Astanid on 21.03.2016.
 */
public class View implements Observer {
    @Override
    public void update(int co2) {
        System.out.println("Current Co2: " + co2);
    }
}
