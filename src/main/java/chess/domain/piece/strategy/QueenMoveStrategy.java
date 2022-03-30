package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public final class QueenMoveStrategy extends AbstractCommonMoveStrategy {

    @Override
    public void isMovable(Position source, Position target) {
        validateSamePosition(source, target);

        if (!source.isDiagonal(target) && !source.isSameRank(target) && !source.isSameFile(target)) {
            throw new IllegalArgumentException("퀸은 상하좌우, 대각선으로만 이동가능합니다.");
        }
    }
}
