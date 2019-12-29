package ru.itis.clicker.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Player {
    private final StringProperty name;
    private final IntegerProperty score;

    public Player(String name) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(0);
    }

    public IntegerProperty getScore() {
        return score;
    }

    public StringProperty getName() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setScore(int score) {
        this.score.set(score);
    }
}
