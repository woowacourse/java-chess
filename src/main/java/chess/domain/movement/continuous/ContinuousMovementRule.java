package chess.domain.movement.continuous;

import chess.domain.movement.MovementRule;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Stream;

public abstract class ContinuousMovementRule implements MovementRule {

    @Override
    public final boolean isMovable(Position start, Position end) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);

        return isMovable(rankDifference, fileDifference);
    }

    @Override
    public final List<Position> findPath(Position start, Position end) {
        if (!isMovable(start, end)) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }
        int amount = calculate(start, end);

        return Stream.iterate(next(start), this::next)
                .limit(amount)
                .toList();
    }

    private int calculate(Position start, Position end) {
        int rankDifference = start.calculateRankDifference(end);
        int fileDifference = start.calculateFileDifference(end);
        int rankDifferenceSize = Math.abs(rankDifference);
        int fileDifferenceSize = Math.abs(fileDifference);

        return Math.max(rankDifferenceSize, fileDifferenceSize);
    }

    protected abstract boolean isMovable(int rankDifference, int fileDifference);

    protected abstract Position next(Position position);
}
