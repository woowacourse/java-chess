package chess.dto;

import chess.domain.piece.Color;

public class TurnFindResponse {

    private final Color color;

    public TurnFindResponse(String color) {
        this.color = Color.nameOf(color);
    }

    public Color getColor() {
        return color;
    }
}
