package techcourse.fp.chess.domain;

import java.util.ArrayList;
import java.util.List;
import techcourse.fp.movingStrategy.LeftDownStrategy;
import techcourse.fp.movingStrategy.LeftUpStrategy;
import techcourse.fp.movingStrategy.MovingStrategies;
import techcourse.fp.movingStrategy.MovingStrategy;
import techcourse.fp.movingStrategy.RightDownStrategy;
import techcourse.fp.movingStrategy.RightUpStrategy;

public class Bishop extends Piece {

    {
        final List<MovingStrategy> rawStrategies = List.of(new RightUpStrategy(), new RightDownStrategy(),
                new LeftDownStrategy(), new LeftUpStrategy());
        strategies = new MovingStrategies(rawStrategies);
    }


    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public List<Position> findPath(final Position sourcePosition, final Position targetPosition,
                                   final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(sourcePosition, targetPosition);

        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        return createPath(sourcePosition, targetPosition, movingStrategy);
    }

    private List<Position> createPath(final Position sourcePosition, final Position targetPosition,
                                      final MovingStrategy movingStrategy) {
        List<Position> path = new ArrayList<>();
        Position currentPosition = sourcePosition;

        do {
            currentPosition = movingStrategy.move(currentPosition);
            path.add(currentPosition);
        } while (!currentPosition.equals(targetPosition));

        return path;
    }

}
