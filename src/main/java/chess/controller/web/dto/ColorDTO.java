package chess.controller.web.dto;

import chess.domain.piece.Color;

public class ColorDTO {
    String color;

    public ColorDTO(Color color) {
        this.color = color.name();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
