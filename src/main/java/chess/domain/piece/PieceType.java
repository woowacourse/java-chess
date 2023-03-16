package chess.domain.piece;

import chess.domain.move.BishopMove;
import chess.domain.move.KingMove;
import chess.domain.move.KnightMove;
import chess.domain.move.Movable;
import chess.domain.move.PawnMove;
import chess.domain.move.QueenMove;
import chess.domain.move.RookMove;

public enum PieceType {
    QUEEN(new QueenMove()),
    ROOK(new RookMove()),
    KNIGHT(new KnightMove()),
    PAWN(new PawnMove()),
    BISHOP(new BishopMove()),
    KING(new KingMove());

    private final Movable movable;

    PieceType(final Movable movable) {
        this.movable = movable;
    }

    public boolean canMove(final Position source, final Position target) {
        return movable.canMove(source, target);
    }
}
