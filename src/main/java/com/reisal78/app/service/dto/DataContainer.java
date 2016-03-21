package com.reisal78.app.service.dto;

import java.util.List;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class DataContainer {
    private int currentCo2;
    private int currentSpeed;
    private boolean status;
    private List<String> messages;

    public DataContainer(int currentCo2, int currentSpeed, boolean status, List<String> messages) {
        this.currentCo2 = currentCo2;
        this.currentSpeed = currentSpeed;
        this.status = status;
        this.messages = messages;
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

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
