package chess.domain.movement.discrete;

import chess.domain.movement.MovementRule;
import chess.domain.position.Position;
import java.util.List;

public abstract class DiscreteMovementRule implements MovementRule {

    @Override
    public final boolean isMovable(Position start, Position end, boolean isEnemyExistAtEnd) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        return isMovable(rankDifference, fileDifference);
    }

    @Override
    public final List<Position> findPath(Position start, Position end, boolean isEnemyExistAtEnd) {
        if (!isMovable(start, end, isEnemyExistAtEnd)) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }

        return List.of(end);
    }

    protected abstract boolean isMovable(int rankDifference, int fileDifference);
}
