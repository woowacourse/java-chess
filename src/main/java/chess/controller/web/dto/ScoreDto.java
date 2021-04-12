package chess.controller.web.dto;

import chess.domain.piece.Color;

public class ScoreDto {
    private final String color;
    private final double value;

    public ScoreDto(Color color, double value) {
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
