package com.reisal78.app.view.controller;

import com.reisal78.app.service.AbstractService;
import com.reisal78.app.service.HarmonyService;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Igor Simagin on 21.03.2016.
 */
public class FameController extends WindowAdapter{

    private AbstractService service;

    public FameController(AbstractService service) {
        this.service = service;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        service.stop();
        super.windowClosing(e);
    }
}
