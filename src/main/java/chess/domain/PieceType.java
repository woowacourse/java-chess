package chess.domain;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.strategy.speical.BishopMoveStrategy;
import chess.domain.strategy.pawn.BlackPawnMoveStrategy;
import chess.domain.strategy.speical.KingMoveStrategy;
import chess.domain.strategy.speical.KnightMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.speical.QueenMoveStrategy;
import chess.domain.strategy.speical.RookMoveStrategy;
import chess.domain.strategy.pawn.WhitePawnMoveStrategy;
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

    public boolean isBlackPawn() {
        return this == BLACK_PAWN;
    }

    public boolean isWhitePawn() {
        return this == WHITE_PAWN;
    }
}
