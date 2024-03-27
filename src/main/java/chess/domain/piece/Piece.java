package chess.domain.piece;

import chess.domain.PieceRelation;
import chess.domain.position.Movement;
import chess.domain.position.PathStatus;

public abstract class Piece {
    protected final PieceColor color;
    protected final PieceType type;

    public Piece(final PieceColor color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isMovable(final Movement movement, final PieceRelation pieceRelation, final PathStatus pathStatus);

    public boolean isColor(final PieceColor color) {
        return this.color == color;
    }

    public boolean isType(final PieceType pieceType) {
        return this.type == pieceType;
    }

    public PieceType type() {
        return type;
    }

    public PieceColor color() {
        return color;
    }
}
