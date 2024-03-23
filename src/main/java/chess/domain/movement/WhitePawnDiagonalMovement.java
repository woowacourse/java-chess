package chess.domain.movement;

import chess.domain.position.Position;
import java.util.List;

public class WhitePawnDiagonalMovement implements MovementRule {

    @Override
    public boolean isMovable(Position start, Position end, boolean isEnemyExistAtEnd) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        boolean isMatchDifference = rankDifference == 1 && Math.abs(fileDifference) == 1;
        return isEnemyExistAtEnd && isMatchDifference;
    }

    @Override
    public List<Position> findPath(Position start, Position end, boolean isEnemyExistAtEnd) {
        if (!isMovable(start, end, isEnemyExistAtEnd)) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }

        return List.of(end);
    }
}
