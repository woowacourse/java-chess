package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;
import domain.piece.info.Vector;

public abstract class Piece {
    private final Color color;
    private final Type type;

    protected Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        if (isSameColor(targetPiece)) {
            return false;
        }

        final Vector vector = new Vector(source, target);

        return isReachable(vector, targetPiece);
    }

    public boolean isSameColor(final Piece piece) {
        return this.color.isSameColor(piece.color);
    }

    protected abstract boolean isReachable(final Vector vector, final Piece targetPiece);

    public boolean isInitPawn() {
        return false;
    }

    public Type type() {
        return type;
    }

    public Color color() {
        return color;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }
    public boolean isEmpty() {
        return false;
    }
}
