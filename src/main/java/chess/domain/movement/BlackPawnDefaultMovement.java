package chess.domain.movement;

import chess.domain.position.Position;
import java.util.List;

public final class BlackPawnDefaultMovement implements MovementRule {

    @Override
    public boolean isMovable(Position start, Position end, boolean isAttack) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        boolean isEmptyAtEnd = !isAttack;
        boolean isMatchDifference = rankDifference == -1 && fileDifference == 0;

        return isEmptyAtEnd && isMatchDifference;
    }

    @Override
    public List<Position> findPath(Position start, Position end, boolean isAttack) {
        if (!isMovable(start, end, isAttack)) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }

        return List.of(end);
    }
}
