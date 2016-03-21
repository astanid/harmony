package com.reisal78.app.service;

import com.reisal78.app.view.Observer;
import com.reisal78.app.model.HarmonyInterface;
import com.reisal78.app.service.dto.DataContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

/**
 * Created by Astanid on 21.03.2016.
 */
public class HarmonyService extends AbstractService {

    private final static Logger LOGGER = LogManager.getLogger(HarmonyService.class);
    public final static int LOG_LENGTH = 10000; //длина лога в окне

    private HarmonyInterface utils;

    Queue<String > messages = new ArrayDeque<>(LOG_LENGTH);

    private boolean isRun = true;
    private final int TIME_INTERVAL = 5000; //интервал запроса
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
                sleep(TIME_INTERVAL);
            }
        utils.destroy();
    }

    private void startVerify(int co2) {

        addMessage("Текущее СО2 - " + co2 );


         //Если со2 на нижнем лимите
        if (co2 <= HarmonyInterface.LIMIT_0 && utils.getStatus()) {
            utils.powerOff(); //выключаем
            addMessage("Выключение установки");
            sleep(TIME_PAUSE); //уснули на 30 сек
        }

        if (co2 > HarmonyInterface.LIMIT_0 && !utils.getStatus()) {
            utils.powerOn();
            addMessage("Установка запущена");
            //в общем если понадобится добавишь сюда
            //utils.setSpeed(1);
            sleep(TIME_PAUSE);
        }

        if (co2 > HarmonyInterface.LIMIT_0 && co2 < HarmonyInterface.LIMIT_1 && utils.getCurrentSpeed() != 1) {
            utils.setSpeed(1);
            addMessage("Включаем скорость " + utils.getCurrentSpeed());
            sleep(TIME_PAUSE);
        }

        if (co2 > HarmonyInterface.LIMIT_1 && co2 < HarmonyInterface.LIMIT_2 && utils.getCurrentSpeed() != 2) {
            utils.setSpeed(2);
            addMessage("Включаем скорость " + utils.getCurrentSpeed());
            sleep(TIME_PAUSE);
        }

        if (co2 > HarmonyInterface.LIMIT_2 && utils.getCurrentSpeed() != 3) {
            utils.setSpeed(3);
            addMessage("Включаем скорость " + utils.getCurrentSpeed());
            sleep(TIME_PAUSE);
        }

    }

    private void addMessage(String message) {
        LOGGER.info(message);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        message ="<b>" + format.format(new Date()) + "</b>: " + message;
        messages.add(String.valueOf(message));
        if (messages.size() > LOG_LENGTH) {
            messages.remove();
        }
        notifyAllObservers();
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

