package ru.itis.clicker.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import ru.itis.clicker.models.Ball;
import ru.itis.clicker.models.Player;
import ru.itis.clicker.services.ClientService;

public class BattleController {
    private Player player;
    private Ball ball;
    private String winner;
    private boolean started;
    private boolean finished;
    private ObjectMapper mapper = new ObjectMapper();
    private ClientService service = new ClientService();

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    @FXML
    public void initialize() {
        this.player = new Player(input.getText());
        score.textProperty().bind(player.getScore().asString());
        name.textProperty().bind(player.getName());
    }

    @FXML
    private Circle circle = new Circle();

    @FXML
    private Label score;

    @FXML
    private Label name;

    @FXML
    private VBox box;

    @FXML
    private TextField input;

    @FXML
    private Button ready;

    @FXML
    public void ready() {
        if (!input.getText().isEmpty()) {
            service.startConnection();
            player.setName(input.getText());
            ObjectNode node = mapper.createObjectNode();
            ObjectNode player = mapper.createObjectNode();
            player.put("name", getPlayer().getName().get());
            player.put("score", getPlayer().getScore().get());
            node.put("player", player);
            ObjectNode nodes = mapper.createObjectNode();
            nodes.put("header", "join");
            nodes.put("payload", node);
            service.sendMessage(nodes.toString());
            box.setDisable(true);
            box.setVisible(false);
        }
    }

    public void start() {
        if (isStarted()) {
            circle.setVisible(true);
            circle.setDisable(false);
            circle.setCenterX(ball.getX());
            circle.setCenterY(ball.getY());
            System.out.println("Circle " + circle.getCenterX() + " " + circle.getCenterY());
        }
    }

    public void updateScene() {
        score.textProperty().bind(player.getScore().asString());
        circle.setCenterX(ball.getX());
        circle.setCenterY(ball.getY());
    }

    public void finish() {
        player.setScore(100);
        score.textProperty().bind(player.getScore().asString());
        if (isFinished()) {
            circle.setVisible(false);
            circle.setDisable(true);
            if (player.getName().get().equals(winner)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game finished");
                alert.setHeaderText("Congratulations!!!");
                alert.setContentText("The winner is " + winner);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game finished");
                alert.setHeaderText("You loosed...");
                alert.setContentText("The winner is " + winner);
                alert.showAndWait();
            }
            ObjectNode node = mapper.createObjectNode();
            node.put("header", "exit");
            service.sendMessage(node.toString());
            //TODO отправка сообщения о выходе на сервер
            StageManager.goToMenuFromGame();
        }
    }

    @FXML
    public void click() {
        ObjectNode node = mapper.createObjectNode();
        ObjectNode player = mapper.createObjectNode();
        player.put("name", this.player.getName().get());
        player.put("name", this.player.getName().get());

        node.put("player", player);
        ObjectNode nodes  = mapper.createObjectNode();
        nodes.put("header", "move");
        nodes.put("payload", node);
        service.sendMessage(nodes.toString());
        //TODO отправка сообщения о нажатии на сервер
    }
}
