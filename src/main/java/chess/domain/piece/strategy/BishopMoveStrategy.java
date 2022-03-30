package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public final class BishopMoveStrategy extends AbstractCommonMoveStrategy {

    @Override
    public void isMovable(Position source, Position target) {
        validateSamePosition(source, target);

        if (!source.isDiagonal(target)) {
            throw new IllegalArgumentException("비숍은 대각선으로만 이동가능 합니다.");
        }
    }
}
