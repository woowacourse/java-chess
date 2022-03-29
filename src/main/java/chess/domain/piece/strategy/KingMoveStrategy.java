package chess.domain.piece.strategy;

import chess.domain.postion.Position;

public class KingMoveStrategy extends AbstractCommonMoveStrategy {
    @Override
    public void isMovable(Position source, Position target) {
        validateSamePosition(source, target);

        if (!source.isInOneSquare(target)) {
            throw new IllegalArgumentException("king은 상하좌우, 대각선 한 칸만 이동할 수 있습니다.");
        }
    }
}
