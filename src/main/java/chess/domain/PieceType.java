package chess.domain;

import chess.domain.strategy.BishopMoveStrategy;
import chess.domain.strategy.BlackPawnMoveStrategy;
import chess.domain.strategy.KingMoveStrategy;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.QueenMoveStrategy;
import chess.domain.strategy.RookMoveStrategy;
import chess.domain.strategy.WhitePawnMoveStrategy;
import java.util.Deque;
import java.util.Map;

public enum PieceType {
    BLACK_PAWN(new BlackPawnMoveStrategy()),
    WHITE_PAWN(new WhitePawnMoveStrategy()),
    ROOK(new RookMoveStrategy()),
    KNIGHT(new KnightMoveStrategy()),
    BISHOP(new BishopMoveStrategy()),
    QUEEN(new QueenMoveStrategy()),
    KING(new KingMoveStrategy()),
    ;

    private final MoveStrategy moveStrategy;

    PieceType(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public Map<Direction, Deque<Position>> calculateAllDirectionPositions(Position currentPosition) {
        return this.moveStrategy.generateMovablePositions(currentPosition);
    }

}
