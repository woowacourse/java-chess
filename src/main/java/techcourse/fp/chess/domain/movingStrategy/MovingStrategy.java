package techcourse.fp.chess.domain.movingStrategy;

import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Position;

public abstract class MovingStrategy {
    protected final List<Direction> directions;

    public MovingStrategy(final List<Direction> directions) {
        this.directions = directions;
    }

    public boolean isMovable(Position source, Position target) {
        final int gapOfFileOrder = target.getFileOrder() - source.getFileOrder();
        final int gapOfRankOrder = target.getRankOrder() - source.getRankOrder();

        return directions.stream()
                .anyMatch(direction -> isReachable(direction, gapOfFileOrder, gapOfRankOrder));
    }

    protected abstract boolean isReachable(final Direction direction, final int gapOfFileOrder, final int gapOfRankOrder);

    public abstract List<Position> createPath(Position source, Position target);
}
