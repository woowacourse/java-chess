package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public final class KnightMoveStrategy extends AbstractCommonMoveStrategy {

    @Override
    public void isMovable(Position source, Position target) {
        validateSamePosition(source, target);

        if (source.isSameFile(target) || source.isSameRank(target)) {
            throw new IllegalArgumentException("나이트는 상하좌우로 이동할 수 없습니다.");
        }

        if (source.isDiagonal(target)) {
            throw new IllegalArgumentException("나이트는 대각선으로 이동할 수 없습니다.");
        }
    }
}
