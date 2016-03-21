package com.reisal78.app;

import com.reisal78.app.model.HarmonyUtils;
import com.reisal78.app.model.HarmonyUtilsMock;
import com.reisal78.app.service.AbstractService;
import com.reisal78.app.service.HarmonyService;
import com.reisal78.app.view.Observer;
import com.reisal78.app.view.controller.FameController;
import com.reisal78.app.view.view.MainFrame;

import java.awt.event.WindowListener;
import java.util.Scanner;

/**
 * Created by Astanid on 21.03.2016.
 */
public class RunApp {
    public static void main(String[] args) {
        HarmonyUtils harmonyUtils = new HarmonyUtilsMock();
        AbstractService service = new HarmonyService(harmonyUtils);
        WindowListener windowListener = new FameController(service);
        Observer observer = new MainFrame("Harmony Utils", windowListener);
        service.registryObserver(observer);

        new Thread(service).start();

    }
}
