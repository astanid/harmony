package com.reisal78.app.model;

import java.io.IOException;

/**
 * Created by Igor Simagin on 21.03.2016.
 * Это класс заглушка, я его использовал как имитатор твоей установки. он тебе не нужен
 */
public class HarmonyInterfaceMock implements HarmonyInterface {


    private int currentSpeed = 0;
    private boolean status = true;

    @Override
    public int getCo2() {
        return (int) (400+Math.random()*500);
    }

    @Override
    public void setSpeed(int speed) {
        try {
            Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Speed" + speed + "\"");
            //   Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Speed" + speed + "\"");

            currentSpeed = speed;
        } catch (IOException e) {
        //    LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public void powerOff() {
        try {
            Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Power Toggle\"");
            status = false;
        } catch (IOException e) {
         //   LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void powerOn() {
        try {
            Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Power Toggle\"");
            status = true;
        } catch (IOException e) {
        //    LOGGER.error(e.getMessage(), e);
        }
    }


    @Override
    public void init() {

    }

    @Override
    public void destroy() {

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
