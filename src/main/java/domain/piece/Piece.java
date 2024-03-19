package domain.piece;

import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Type;

public abstract class Piece {
    private final Color color;
    private final Position position;

    protected Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public Position position() {
        return position;
    }

    public Color color() {
        return color;
    }

    public abstract Type shape();

    public abstract boolean isMovable();
}
