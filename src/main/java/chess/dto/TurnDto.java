package chess.dto;

import chess.domain.color.Color;

public record TurnDto(String color) {

    public Color getColor() {
        return Color.valueOf(color);
    }
}
