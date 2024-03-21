package domain.piece;

import domain.position.Position;

public abstract class Piece {
    protected final String name;
    protected final PieceColor color;
    protected final PieceType type;

    public Piece(final String name, final PieceColor color, PieceType type) {
        this.name = name;
        this.color = color;
        this.type = type;
    }

    public abstract boolean isInMovableRange(final Position source, final Position target);

    public boolean isColor(final PieceColor color) {
        return this.color == color;
    }

    public boolean isType(PieceType pieceType) {
        return this.type == pieceType;
    }

    public String name() {
        return name;
    }

    public PieceColor color() {
        return color;
    }
}
