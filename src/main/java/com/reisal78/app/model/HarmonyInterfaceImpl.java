package com.reisal78.app.model;

import com.eschava.ht2000.usb.HT2000State;
import com.eschava.ht2000.usb.HT2000UsbConnection;
import com.eschava.ht2000.usb.UsbException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class HarmonyInterfaceImpl implements HarmonyInterface {

    public static final Logger LOGGER = LogManager.getLogger(HarmonyInterfaceImpl.class);


    private HT2000UsbConnection usbConnection = null;
    private int currentSpeed = 0;
    private boolean status = true;


    @Override
    public void init() {
        try {
            usbConnection = new HT2000UsbConnection();
            usbConnection.open();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            System.exit(-1);
        }
        //при запуске программы включаем установку, и ставим скорость 1
        //она уже запущена при старте программы
       // powerOn();
       // setSpeed(1);
    }


    @Override
    public int getCo2() {
        int co2;
        HT2000State state = null;
        try {
            state = usbConnection.readState();
            co2 = state.getCo2();
            return co2;
        } catch (UsbException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return 600;
    }

    @Override
    public void setSpeed(int speed) {
        try {
            Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Speed" + speed + "\"");
         //   Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Speed" + speed + "\"");

            currentSpeed = speed;
            LOGGER.debug(">> change speed" + currentSpeed);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public void powerOff() {
        try {
            Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Power Toggle\"");
            status = false;
            LOGGER.debug(">> Power Off");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void powerOn() {
        try {
            Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Power Toggle\"");
            status = true;
            LOGGER.debug(">> Power ON");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    @Override
    public void destroy() {
        //setSpeed(1);
        //powerOff();
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
