package chess.model.piece;

import chess.model.Color;

public abstract class Piece {

    private final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract boolean movable();

    public abstract String getLetter();

    public boolean isBlack() {
        return color.isBlack();
    }
}
