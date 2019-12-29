package ru.itis.clicker.controllers;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageManager {
    private Map<String, String> routes;
    private static Stage mainStage;
    private static StageManager manager;
    private static SplashController splashController;

    public static void init(Stage stage) {
        manager = new StageManager(stage);
    }

    private StageManager(Stage stage) {
        routes = new HashMap<>();
        mainStage = stage;
        mainStage.setResizable(false);
    }

    public static void put(String name, String path) {
        manager.routes.put(name, path);
    }

    public static void goTo(String destination) {
        try {
            Parent root = FXMLLoader.load(StageManager.class.getResource(manager.routes.get(destination)));
            mainStage.setTitle("Clicker");
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void goToMenuFromGame() {
        splashController.setDest("menu");
        goTo("splash");
    }

    public static void goToGameFromMenu() {
        splashController.setDest("game");
        goTo("splash");
    }

    public static void close() {
        mainStage.close();
    }
}
