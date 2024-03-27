package chess.domain.piece;

import chess.domain.PieceRelation;
import chess.domain.position.Movement;

public abstract class Piece {
    protected final PieceColor color;
    protected final PieceType type;

    public Piece(final PieceColor color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isMovable(final Movement movement, final PieceRelation pieceRelation, boolean isOpened);

    public boolean isColor(final PieceColor color) {
        return this.color == color;
    }

    public PieceColor color() {
        return color;
    }

    public PieceType type() {return type;}
}
