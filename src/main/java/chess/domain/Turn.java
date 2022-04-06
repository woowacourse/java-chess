package chess.domain;

import chess.domain.piece.Color;

public class Turn {

    Color color;

    public Turn() {
        this.color = Color.WHITE;
    }

    public boolean isRightTurn(String color) {
        return this.color.name().equalsIgnoreCase(color);
    }

    public void nextTurn() {
        if (color == Color.BLACK) {
            color = Color.WHITE;
            return;
        }
        if (color == Color.WHITE) {
            color = Color.BLACK;
        }
    }

    public String getColor() {
        return color.name().toLowerCase();
    }
}
