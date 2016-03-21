package com.reisal78.app;

import com.eschava.ht2000.usb.HT2000State;
import com.eschava.ht2000.usb.HT2000UsbConnection;
import com.eschava.ht2000.usb.UsbException;

import java.io.IOException;

/**
 * Created by Astanid on 15.03.2016.
 */
public class RunAppOld {

    private static int fanSpeed = 0;
    private static boolean powerOn = true;

    public static void main(String[] args) {
//        /* Завершение работы (SIGTERM и т.д.) /
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            public void run() {
//                System.exit(1);
//            }
//        });
        int co2=600;
        int limit0=500; //уровень отключения
        int limit1=650; //желаемая величина
        int limit2=800; // предел для включения макс скорости
        boolean powerOn=true;

        HT2000UsbConnection usbConnection = null;
        try {

            usbConnection = new HT2000UsbConnection();
            usbConnection.open();


            while (true) {

                HT2000State state = usbConnection.readState();
//                changeSpeed(state.getCo2());
                co2=state.getCo2();
                System.out.println("co2 = " + co2 + " " + fanSpeed + " " + powerOn);
                if (co2 <= limit0 && powerOn==true) {
                    //Выключение
                    Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Power Toggle\"");
                    powerOn = false;
                    System.out.println("выключаем установку");
                } else if (co2 >= limit0+50 && powerOn == false) {
                        //Включение
                        Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Power Toggle\"");
                        powerOn = true;
                        System.out.println("включаем установку");
                } else if (co2 > limit0 && co2 <= limit1 && fanSpeed != 1) {
                    Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Speed1\"");
                    fanSpeed = 1;
                    System.out.println(" включаем скорость " + fanSpeed);

                } else if (co2 > limit1 && co2 <= limit2 && fanSpeed != 2) {
                    Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Speed2\"");
                    fanSpeed = 2;
                    System.out.println(" включаем скорость " + fanSpeed);
                    Thread.sleep(30000);
                } else if (co2 >= limit2 && fanSpeed != 3) {
                    Runtime.getRuntime().exec("node c:\\Harmony\\AutoVent\\harmonyHubCLI\\harmonyHubCli.js -l 192.168.1.33 -d \"VENTS VUT2\" -c \"Speed3\"");
                    fanSpeed = 3;
                    Thread.sleep(30000);
                    System.out.println(" включаем скорость " + fanSpeed);
                }
                Thread.sleep(5000);
            }

        } catch (UsbException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            usbConnection.close();
            HT2000UsbConnection.shutdown();
        }
    }

    private static void changeSpeed(int co2) throws IOException {
        System.out.println("co2 = " + co2 + " " + fanSpeed + " " + powerOn);

    }
}