package com.reisal78.app.model;

import com.eschava.ht2000.usb.HT2000State;
import com.eschava.ht2000.usb.HT2000UsbConnection;
import com.eschava.ht2000.usb.UsbException;
import com.reisal78.app.model.HarmonyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class HarmonyUtilsMock implements HarmonyUtils {

    private static final Logger LOGGER = LogManager.getLogger(HarmonyUtilsMock.class);


    private HT2000UsbConnection usbConnection = null;
    private int currentSpeed;
    private boolean status;


    @Override
    public void init() {
        try {
            usbConnection = new HT2000UsbConnection();
            usbConnection.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        powerOn();
        setSpeed(0);
        LOGGER.info("Application is Run");
    }


    @Override
    public int getCo2() {
        int co2 = 0;
        HT2000State state = null;
        try {
            state = usbConnection.readState();
            co2 = state.getCo2();
            LOGGER.info("- " + co2 + " - " + currentSpeed + " - " + status); /*((status) ? "on" : "off"));*/
            return co2;
        } catch (UsbException e) {
            e.printStackTrace();
        }
        return 0;
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
    public void destroy() {
        setSpeed(0);
        powerOff();
        LOGGER.info("Application is close");
        usbConnection.close();
        HT2000UsbConnection.shutdown();
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
