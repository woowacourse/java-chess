package chess.domain.piece;

import chess.domain.Color;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public boolean isBlack() {
        return color.isBlack();
    }

    public boolean isEmpty() {
        return false;
    }

    public abstract String getNotation();
}
