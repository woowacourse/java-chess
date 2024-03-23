package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Type;
import domain.piece.info.Vector;

public abstract class Piece {
    private final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public Piece move() {
        return this;
    }

    public boolean isReachable(final Vector vector, final Piece targetPiece) {
        if (this.isSameColorWith(targetPiece)) {
            return false;
        }

        return isInstanceReachable(vector, targetPiece);
    }

    public boolean isSameColorWith(final Piece piece) {
        return this.color.isSameColor(piece.color);
    }

    protected abstract boolean isInstanceReachable(final Vector vector, final Piece targetPiece);

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean hasColor(final Color color) {
        return this.color == color;
    }

    public Color color() {
        return color;
    }
}
