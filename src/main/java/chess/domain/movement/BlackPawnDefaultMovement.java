package chess.domain.movement;

import chess.domain.position.Position;
import java.util.List;

public class BlackPawnDefaultMovement implements MovementRule {

    @Override
    public final boolean isMovable(Position start, Position end, boolean isEnemyExistAtEnd) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        boolean isEmptyAtEnd = !isEnemyExistAtEnd;
        boolean isMatchDifference = rankDifference == -1 && fileDifference == 0;

        return isEmptyAtEnd && isMatchDifference;
    }

    @Override
    public final List<Position> findPath(Position start, Position end, boolean isEnemyExistAtEnd) {
        if (!isMovable(start, end, isEnemyExistAtEnd)) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }

        return List.of(end);
    }
}
