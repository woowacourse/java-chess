package chess.domain.strategy;

import chess.domain.position.Position;

public class RookMoveStrategy implements MoveStrategy {
    @Override
    public void isMovable(Position source, Position target) {
        if (source.isSameFile(target) && source.isSameRank(target)) {
            throw new IllegalStateException("제자리에 머무를 수 없습니다.");
        }

        if (!source.isSameFile(target) && !source.isSameRank(target)) {
            throw new IllegalStateException("상하좌우로만 움직일 수 있습니다.");
        }
    }
}
