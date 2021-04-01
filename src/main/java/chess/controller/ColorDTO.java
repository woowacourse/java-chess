package chess.controller;

import chess.domain.piece.Color;

public class ColorDTO {
    String color;

    public ColorDTO(Color color) {
        this.color = color.name();
    }
}
