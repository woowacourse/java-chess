package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public abstract class Piece {
    private final Color color;
    private final Type type;

    protected Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
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

    public abstract boolean isMovable();
}
