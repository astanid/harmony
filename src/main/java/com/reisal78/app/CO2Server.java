package com.reisal78.app;

import com.eschava.ht2000.usb.HT2000State;
import com.eschava.ht2000.usb.HT2000UsbConnection;
import com.eschava.ht2000.usb.UsbException;

/**
 * Created by Astanid on 21.03.2016.
 */
public class CO2Server extends AbstractServer {

    private boolean isRun = true;
    private int timeSleep = 5000;
    private int currentCO2 = 0;

    @Override
    public void run() {

        HT2000UsbConnection usbConnection = null;
        try {
            usbConnection = new HT2000UsbConnection();
            usbConnection.open();

            while (isRun) {
                HT2000State state = usbConnection.readState();
                currentCO2 = state.getCo2();
                notifyAllObservers();
                Thread.sleep(timeSleep);
            }
        } catch (UsbException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            usbConnection.close();
            HT2000UsbConnection.shutdown();
            System.out.println("Server OFF");
        }

    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(currentCO2);
        }

    }

    @Override
    public void stop() {
        setRun(false);
        System.out.println("Ожидаем завершение работы...");
    }
}

