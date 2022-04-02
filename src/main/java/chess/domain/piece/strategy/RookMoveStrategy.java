package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public final class RookMoveStrategy extends AbstractCommonMoveStrategy {

    @Override
    public void isMovable(Position source, Position target) {
        validateSamePosition(source, target);

        if (!source.isSameRank(target) && !source.isSameFile(target)) {
            throw new IllegalArgumentException("룩은 상하좌우로만 이동가능 합니다.");
        }
    }
}
