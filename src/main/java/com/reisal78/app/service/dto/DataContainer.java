package com.reisal78.app.service.dto;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class DataContainer {
    private int currentCo2;
    private int currentSpeed;
    private boolean status;

    public DataContainer(int currentCo2, int currentSpeed, boolean status) {
        this.currentCo2 = currentCo2;
        this.currentSpeed = currentSpeed;
        this.status = status;
    }

    public int getCurrentCo2() {
        return currentCo2;
    }

    public void setCurrentCo2(int currentCo2) {
        this.currentCo2 = currentCo2;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
