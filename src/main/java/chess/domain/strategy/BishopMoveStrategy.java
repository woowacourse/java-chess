package chess.domain.strategy;

import chess.domain.ChessBoard;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public final class BishopMoveStrategy extends CommonMovingStrategy {

    @Override
    public void isMovable(Position source, Position target, ChessBoard chessBoard) {
        checkCommonCondition(source, target, chessBoard);

        Direction direction = Direction.of(source, target);

        if (!direction.isDiagonalDirection()) {
            throw new IllegalArgumentException("대각선으로만 움직일 수 있습니다.");
        }
    }
}
