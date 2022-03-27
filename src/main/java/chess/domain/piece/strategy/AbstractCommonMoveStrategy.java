package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public abstract class AbstractCommonMoveStrategy implements MoveStrategy {

    protected void validateSamePosition(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("source와 target은 같을 수 없습니다.");
        }
    }

    protected void validateTopBottomRightLeft(Position source, Position target) {
        if (!source.isSameRank(target) || !source.isSameFile(target)) {
            throw new IllegalArgumentException("target은 source의 상하좌우에 있어야 합니다.");
        }
    }

    protected void validateDiagonal(Position source, Position target) {
        if (!source.isDiagonal(target)) {
            throw new IllegalArgumentException("source와 target은 대각선이여야 합니다.");
        }
    }
}
