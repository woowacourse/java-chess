package chess.model.piece;

import chess.model.piece.strategy.*;
import chess.model.position.Movement;

public enum Type {
    BISHOP(new BishopStrategy()),
    ROOK(new RookStrategy()),
    QUEEN(new QueenStrategy()),
    KNIGHT(new KnightStrategy()),
    PAWN(new UnsupportedStrategy()),
    KING(new KingStrategy()),
    NONE(new UnsupportedStrategy());

    private final PieceStrategy pieceStrategy;

    Type(PieceStrategy pieceStrategy) {
        this.pieceStrategy = pieceStrategy;
    }

    public boolean canMove(Movement movement) {
        return pieceStrategy.canMove(movement);
    }
}
