package ru.itis.clicker.main;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.itis.clicker.controllers.StageManager;

public class Main extends Application {
    private static final int PORT = 1234;
    //TODO реализовать работу клиента
    public static void main(String[] args) {
        //TODO реализовать ответы серверу
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageManager.init(primaryStage);
        StageManager.put("splash", "/fxml/splash.fxml");
        StageManager.put("menu", "/fxml/menu.fxml");
        StageManager.put("game", "/fxml/battle.fxml");
        StageManager.goToMenuFromGame();
    }
}
