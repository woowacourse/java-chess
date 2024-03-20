package chess.domain.piece;

import chess.domain.position.Square;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.PawnMoveStrategy;

public enum PieceType {

    KING(new PawnMoveStrategy()),
    QUEEN(new PawnMoveStrategy()),
    ROOK(new PawnMoveStrategy()),
    BISHOP(new PawnMoveStrategy()),
    KNIGHT(new KnightMoveStrategy()),
    PAWN(new PawnMoveStrategy()),
    EMPTY(new PawnMoveStrategy()),
    ;

    private final MoveStrategy moveStrategy;

    PieceType(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public boolean canMove(Square source, Square destination, ColorType colorType) {
        return moveStrategy.check(source, destination, colorType);
    }
}
