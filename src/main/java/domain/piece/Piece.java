package domain.piece;

import domain.position.Position;

public abstract class Piece {
    protected final String name;
    protected final PieceColor color;

    public Piece(final String name, final PieceColor color) {
        this.name = name;
        this.color = color;
    }

    public boolean isColor(final PieceColor color) {
        return this.color == color;
    }

    public abstract boolean isMovable(final Position source, final Position target);

    public String name() {
        return name;
    }
}
