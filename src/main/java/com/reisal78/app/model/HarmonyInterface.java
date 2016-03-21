package com.reisal78.app.model;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public interface HarmonyInterface {

    public static final int CO2 = 600;
    public static final int LIMIT_0 = 500; //уровень отключения
    public static final int LIMIT_1 = 650; //желаемая величина
    public static final int LIMIT_2 = 800; // предел для включения макс скорости


    int getCo2();

    void setSpeed(int speed);

    void powerOff();

    void powerOn();

    void init();

    void destroy();

    int getCurrentSpeed();

    boolean getStatus();
}
