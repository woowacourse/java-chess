package chess.controller;

import chess.domain.piece.Color;

public class ScoreDTO {
    private final String color;
    private final double value;

    public ScoreDTO(Color color, double value) {
        this.color = color.name();
        this.value = value;
    }


    public String getColor() {
        return color;
    }

    public double getValue() {
        return value;
    }
}
