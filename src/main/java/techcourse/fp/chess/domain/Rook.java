package techcourse.fp.chess.domain;

import java.util.ArrayList;
import java.util.List;
import techcourse.fp.movingStrategy.DownStrategy;
import techcourse.fp.movingStrategy.LeftStrategy;
import techcourse.fp.movingStrategy.MovingStrategy;
import techcourse.fp.movingStrategy.RightStrategy;
import techcourse.fp.movingStrategy.UpStrategy;

public class Rook extends Piece {

    {
        strategies = List.of(new UpStrategy(), new DownStrategy(),
                new LeftStrategy(), new RightStrategy());
    }

    public Rook(final Color color) {
        super(color);
    }


    @Override
    public List<Position> findPath(final Position sourcePosition, final Position targetPosition,
                                   final Color targetColor) {
        final MovingStrategy movingStrategy = findStrategy(sourcePosition, targetPosition);

        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        return createPath(sourcePosition, targetPosition, movingStrategy);
    }

    private MovingStrategy findStrategy(final Position sourcePosition, final Position targetPosition) {
        return strategies.stream()
                .filter(strategy -> strategy.movable(sourcePosition, targetPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("행마법상 이동 불가능한 지역입니다."));
    }

    private static List<Position> createPath(final Position sourcePosition, final Position targetPosition,
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
