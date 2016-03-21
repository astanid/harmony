package com.reisal78.app.model;

/**
 * Created by Igor Simagin on 21.03.2016.
 * Это класс заглушка, я его использовал как имитатор твоей установки. он тебе не нужен
 */
public class HarmonyInterfaceMock implements HarmonyInterface {

    private int currentSpeed;
    private boolean status;

    @Override
    public int getCo2() {
        return (int) (Math.random()*650);
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
