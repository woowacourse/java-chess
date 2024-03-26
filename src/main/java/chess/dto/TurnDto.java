package chess.dto;

import chess.domain.color.Color;

public record TurnDto(String color) {

    public static TurnDto of(Color color) {
        return new TurnDto(color.name());
    }

    public Color getColor() {
        return Color.valueOf(color);
    }
}
