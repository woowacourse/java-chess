package chess.domain.movement;

import chess.domain.position.Position;
import java.util.List;

public final class BlackPawnDiagonalMovement implements MovementRule {

    @Override
    public boolean isMovable(Position start, Position end, boolean isAttack) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        boolean isMatchDifference = rankDifference == -1 && Math.abs(fileDifference) == 1;
        return isAttack && isMatchDifference;
    }

    @Override
    public List<Position> findPath(Position start, Position end, boolean isAttack) {
        if (!isMovable(start, end, isAttack)) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }

        return List.of(end);
    }
}
