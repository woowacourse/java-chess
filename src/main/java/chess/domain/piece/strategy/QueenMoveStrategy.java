package chess.domain.piece.strategy;

import chess.domain.piece.info.Position;

public class QueenMoveStrategy extends AllMoveStrategy {
    private static final String QUEEN_ERROR = "[ERROR] 퀸 이동 규칙에 어긋납니다.";

    @Override
    public boolean canMove(Position source, Position target) {
        validateQueenMove(source, target);
        return true;
    }

    private void validateQueenMove(Position source, Position target) {
        if (isNotCrossOrDiagonal(source, target)) {
            throw new IllegalArgumentException(QUEEN_ERROR);
        }
    }
}
