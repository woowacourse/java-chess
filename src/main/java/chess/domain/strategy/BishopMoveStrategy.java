package chess.domain.strategy;

import chess.domain.ChessBoard;
import chess.domain.position.Position;

public final class BishopMoveStrategy extends CommonMovingStrategy {

    @Override
    public void isMovable(Position source, Position target, ChessBoard chessBoard) {
        checkCommonCondition(source, target, chessBoard);

        if (!source.isDiagonal(target)) {
            throw new IllegalArgumentException("대각선이 아닙니다.");
        }
    }
}
