package chess.domain;

import chess.domain.piece.Color;

public class Turn {

    private Color color;

    public Turn(Color color) {
        this.color = color;
    }

    public void changeTurn() {
        if (color.isBlack()) {
            color = Color.WHITE;
        }
        if (color.isWhite()) {
            color = Color.BLACK;
        }
    }
}
