package ru.itis.clicker.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private boolean gameStarted;
    private List<Player> players;
    private boolean gameFinished;
}
