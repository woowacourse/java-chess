package chess.domain.piece;

import chess.domain.board.Movement;
import chess.domain.board.PieceRelation;

public abstract class Piece {
    protected final PieceColor color;
    protected final PieceType type;

    public Piece(final PieceColor color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract boolean isMovable(final Movement movement, final PieceRelation pieceRelation);

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
