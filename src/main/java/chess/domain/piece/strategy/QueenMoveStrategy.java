package chess.domain.piece.strategy;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Position;

public class QueenMoveStrategy extends AllMoveStrategy {
    private static final String QUEEN_ERROR = "[ERROR] 퀸 이동 규칙에 어긋납니다.";

    @Override
    public boolean canMove(Position source, Position target, ChessBoard chessBoard) {
        validateQueenMove(source, target);
        if (source.isCross(target)) {
            validateCross(source, target, chessBoard);
        }
        if (source.isDiagonal(target)) {
            validateDiagonal(source, target, chessBoard);
        }
        return true;
    }

    private void validateQueenMove(Position source, Position target) {
        if (isNotCrossOrDiagonal(source, target)) {
            throw new IllegalArgumentException(QUEEN_ERROR);
        }
    }
}
