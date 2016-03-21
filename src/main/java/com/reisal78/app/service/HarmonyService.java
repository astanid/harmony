package com.reisal78.app.service;

import com.reisal78.app.view.Observer;
import com.reisal78.app.model.HarmonyInterface;
import com.reisal78.app.service.dto.DataContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Astanid on 21.03.2016.
 */
public class HarmonyService extends AbstractService {

    private final static Logger LOGGER = LogManager.getLogger(HarmonyService.class);
    public final static int LOG_LENGTH = 10000;

    private HarmonyInterface utils;

    Queue<String > messages = new ArrayDeque<>(LOG_LENGTH);

    private boolean isRun = true;
    private final int TIME_INTERVAL = 5000;
    private final int TIME_PAUSE = 30000;

    public HarmonyService(HarmonyInterface utils) {
        this.utils = utils;
    }

    @Override
    public void run() {
        utils.init();
        addMessage("Подключение к устройству установленно");
            while (isRun) {
                startVerify(utils.getCo2());
                notifyAllObservers();
                sleep(TIME_INTERVAL);
            }
        utils.destroy();
    }

    private void startVerify(int co2) {


    }

    private void addMessage(String message) {
        messages.add(String.valueOf(message));
        if (messages.size() > LOG_LENGTH) {
            messages.remove();
        }

    }


    public void sleep(int mc) {
        try {
            Thread.sleep(mc);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(new DataContainer(utils.getCo2(), utils.getCurrentSpeed(), utils.getStatus(), new ArrayList<>(messages)));
        }

    }

    @Override
    public void stop() {
        setRun(false);
    }
}

