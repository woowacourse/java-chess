package chess.domain.piece;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import chess.domain.strategy.BishopMoveStrategy;
import chess.domain.strategy.BlackPawnMoveStrategy;
import chess.domain.strategy.KingMoveStrategy;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.QueenMoveStrategy;
import chess.domain.strategy.RookMoveStrategy;
import chess.domain.strategy.WhitePawnMoveStrategy;
import java.util.Map;
import java.util.Queue;

public enum PieceType {
    BLACK_PAWN(new BlackPawnMoveStrategy()),
    WHITE_PAWN(new WhitePawnMoveStrategy()),
    ROOK(new RookMoveStrategy()),
    KNIGHT(new KnightMoveStrategy()),
    BISHOP(new BishopMoveStrategy()),
    QUEEN(new QueenMoveStrategy()),
    KING(new KingMoveStrategy());

    private final MoveStrategy moveStrategy;

    PieceType(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public Map<Direction, Queue<Position>> generateAllDirectionPositions(Position currentPosition) {
        return this.moveStrategy.generateMovablePositions(currentPosition);
    }
}
