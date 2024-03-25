package domain.dto;

import domain.piece.Color;

public record TurnDto(String color) {
    public Color getTurn() {
        return Color.valueOf(color);
    }
}
