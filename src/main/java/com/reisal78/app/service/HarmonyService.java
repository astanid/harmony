package com.reisal78.app.service;

import com.reisal78.app.view.Observer;
import com.reisal78.app.model.HarmonyUtils;
import com.reisal78.app.service.dto.DataContainer;

/**
 * Created by Astanid on 21.03.2016.
 */
public class HarmonyService extends AbstractService {

    private HarmonyUtils utils;

    private boolean isRun = true;
    private int timeSleep = 5000;
    private int currentCO2 = 0;
    private int currentSpeed = 0;
    private boolean status = false;

    public HarmonyService(HarmonyUtils utils) {
        this.utils = utils;
    }

    @Override
    public void run() {
        utils.init();
        try {
            while (isRun) {
                currentCO2 = utils.getCo2();
                notifyAllObservers();
                Thread.sleep(timeSleep);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        utils.destroy();
    }



    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(new DataContainer(currentCO2, currentSpeed, status));
        }

    }

    @Override
    public void stop() {
        setRun(false);
        System.out.println("Ожидаем завершение работы...");
    }
}

