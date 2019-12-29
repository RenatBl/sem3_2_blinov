package ru.itis.clicker.controllers;


import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.Timer;
import java.util.TimerTask;

public class SplashController {
    @FXML
    public void initialize() {
        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    boolean isTimer = false;

                    @Override
                    public void run() {
                        if (!isTimer) {
                            isTimer = true;
                            timer.cancel();
                            timer.cancel();
                            StageManager.goTo(dest);
                        }
                    }
                });
            }
        }, 3000, 100);
    }

    private static String dest;

    public static void setDest(String dest) {
        SplashController.dest = dest;
    }
}
