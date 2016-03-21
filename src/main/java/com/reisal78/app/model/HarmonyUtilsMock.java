package com.reisal78.app.model;

import com.reisal78.app.model.HarmonyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class HarmonyUtilsMock implements HarmonyUtils {

    private static final Logger LOGGER = LogManager.getLogger(HarmonyUtilsMock.class);


    private int currentSpeed;
    private boolean status;

    @Override
    public int getCo2() {
        int co2 = (int) (Math.random() * 650);
        LOGGER.debug(co2);

        return co2;
    }

    @Override
    public void setSpeed(int speed) {
        currentSpeed = speed;
    }

    @Override
    public void powerOff() {
        status = false;
    }

    @Override
    public void powerOn() {
        status = true;
    }

    @Override
    public void init() {
        powerOn();
        setSpeed(0);
        System.out.println("On");
    }

    @Override
    public void destroy() {
        setSpeed(0);
        powerOff();
        System.out.println("Off");
        System.exit(0);
    }

    @Override
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public boolean getStatus() {
        return status;
    }
}
