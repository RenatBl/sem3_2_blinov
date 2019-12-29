package ru.itis.clicker.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.clicker.controllers.BattleController;
import ru.itis.clicker.models.Ball;
import ru.itis.clicker.models.Player;

public class GameService {
    private ObjectMapper mapper = new ObjectMapper();
    private BattleController controller = new BattleController();

    public void updateBall(JsonNode payload) {
        controller.setBall(new Ball(payload.path("ball").path("x").asDouble(),
                payload.path("ball").path("y").asDouble()));
    }

    public void updatePlayer(JsonNode payload) {
        Player player = new Player(payload.path("player").path("name").asText());
        player.setScore(payload.path("player").path("score").asInt());
        controller.setPlayer(player);
    }
}
