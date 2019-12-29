package ru.itis.clicker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.clicker.controllers.BattleController;
import ru.itis.clicker.models.Ball;
import ru.itis.clicker.models.Player;

public class JsonService {
    private BattleController controller = new BattleController();
    private ObjectMapper mapper = new ObjectMapper();

    public void dispatch(String json) {
        try {
            JsonNode node = mapper.readTree(json);
            switch (node.path("header").asText()) {
                case "start": {
                    start(node.path("ball"));
                    break;
                }
                case "game": {
                    game(node.path("payload"));
                    break;
                }
                case "finish": {
                    finish(node.path("payload"));
                    break;
                }
            }
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private void start(JsonNode payload) {
        controller.setStarted(true);
        controller.setBall(new Ball(payload.path("x").asDouble(),
                payload.path("y").asDouble()));
        controller.start();
    }

    private void game(JsonNode payload) {
        controller.setBall(new Ball(payload.path("ball").path("x").asDouble(),
                payload.path("ball").path("y").asDouble()));
        Player player = new Player(payload.path("player").path("name").asText());
        player.setScore(payload.path("player").path("score").asInt());
        controller.setPlayer(player);
        controller.updateScene();
    }

    private void finish(JsonNode payload) {
        controller.setFinished(true);
        controller.setWinner(payload.path("winner").asText());
        controller.finish();
    }
}
