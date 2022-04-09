package chess.domain.strategy;

import chess.domain.ChessBoard;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public final class RookMoveStrategy extends CommonMovingStrategy {
    @Override
    public void isMovable(Position source, Position target, ChessBoard chessBoard) {
        checkCommonCondition(source, target, chessBoard);

        Direction direction = Direction.of(source, target);

        if (!direction.isLinearDirection()) {
            throw new IllegalArgumentException("상하좌우로만 움직일 수 있습니다.");
        }
    }
}
