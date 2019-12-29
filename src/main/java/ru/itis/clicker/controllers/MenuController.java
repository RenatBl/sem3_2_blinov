package ru.itis.clicker.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.itis.clicker.services.ClientService;

//TODO сделать проверку на заполненность поля хост и отправку для подключения
public class MenuController {
    private ClientService clientService = new ClientService();

    public void startGame() {
        if (!tf.getText().isEmpty()) {
            clientService.setHost(tf.getText());
            StageManager.goToGameFromMenu();
        }
    }

    public void exitGame() {
        StageManager.close();
    }

    @FXML
    private TextField tf;
}
