package com.reisal78.app;

import java.util.Scanner;

/**
 * Created by Astanid on 21.03.2016.
 */
public class RunApp {
    public static void main(String[] args) {
        AbstractServer server = new CO2Server();
        Observer observer = new View();
        server.registryObserver(observer);

        new Thread(server).run();

        Scanner scanner = new Scanner(System.in);
        System.out.println("'q' for exit");

        if (scanner.nextLine().equalsIgnoreCase("q")) {
            server.stop();
        }


    }
}
