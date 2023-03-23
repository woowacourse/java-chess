package techcourse.fp.chess.domain.movingStrategy;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Position;

public class NoneSlidingStrategy extends MovingStrategy {

    public NoneSlidingStrategy(final List<Direction> directions) {
        super(directions);
    }

    @Override
    public List<Position> createPath(final Position source, final Position target) {
        final int gapOfFileOrder = target.getFileOrder() - source.getFileOrder();
        final int gapOfRankOrder = target.getRankOrder() - source.getRankOrder();

        if (isNotMovable(gapOfFileOrder, gapOfRankOrder)) {
            throw new IllegalArgumentException("행마법 상 이동할 수 없는 위치입니다.");
        }

        return Collections.emptyList();
    }

    private boolean isNotMovable(final int gapOfFileOrder, final int gapOfRankOrder) {
        return directions.stream()
                .noneMatch(direction -> isReachable(direction, gapOfFileOrder, gapOfRankOrder));
    }

    @Override
    protected boolean isReachable(final Direction direction, final int gapOfFileOrder, final int gapOfRankOrder) {
        return direction.getFile() == gapOfFileOrder && direction.getRank() == gapOfRankOrder;
    }
}
