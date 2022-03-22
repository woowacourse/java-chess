package chess.domain.piece;

import chess.domain.Color;

public abstract class Piece {
    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    abstract boolean movable(String position, Piece target);

    protected boolean hasSameColor(Piece another) {
        return this.color == another.color;
    }
}
