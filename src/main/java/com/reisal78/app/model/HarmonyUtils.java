package com.reisal78.app.model;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public interface HarmonyUtils {
    int getCo2();

    void setSpeed(int speed);

    void powerOff();

    void powerOn();

    void init();

    void destroy();

    int getCurrentSpeed();

    boolean getStatus();
}
