package chess.domain.piece;

import chess.domain.position.Square;
import chess.domain.strategy.*;

public enum PieceType {

    KING(new KingMoveStrategy()),
    QUEEN(new QueenMoveStrategy()),
    ROOK(new RookMoveStrategy()),
    BISHOP(new BishopMoveStrategy()),
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
