package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public final class PawnMoveStrategy extends AbstractCommonMoveStrategy {

    @Override
    public void isMovable(Position source, Position target) {
        validateSamePosition(source, target);

        if (source.isSameRank(target)) {
            throw new IllegalArgumentException("폰은 가로로 이동할 수 없습니다.");
        }

        if (!source.isInOneSquare(target) && !source.isTwoRankDifference(target)) {
            throw new IllegalArgumentException("폰은 두 칸보다 크게 이동할 수 없습니다.");
        }
    }
}
