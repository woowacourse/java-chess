package chess.domain.strategy;

import chess.domain.ChessBoard;
import chess.domain.position.Position;

public class BishopMoveStrategy implements MoveStrategy {

    @Override
    public void isMovable(Position source, Position target, ChessBoard chessBoard) {
        if (source.isSameFile(target) && source.isSameRank(target)) {
            throw new IllegalStateException("제자리에 머무를 수 없습니다.");
        }

        if (!source.isDiagonal(target)) {
            throw new IllegalStateException("대각선이 아닙니다.");
        }
    }
}
