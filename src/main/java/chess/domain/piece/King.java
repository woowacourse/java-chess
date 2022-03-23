package chess.domain.piece;

import chess.domain.Color;

public class King {

    private static final String NOTATION = "K";

    private final Color color;

    public King(Color color) {
        this.color = color;
    }

    public String getNotation() {
        if (color == Color.WHITE) {
            return NOTATION.toLowerCase();
        }
        return NOTATION;
    }
}
