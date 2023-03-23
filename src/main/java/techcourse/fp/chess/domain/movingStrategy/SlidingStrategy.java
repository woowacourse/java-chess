package techcourse.fp.chess.domain.movingStrategy;

import java.util.ArrayList;
import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;

public class SlidingStrategy extends MovingStrategy {

    public SlidingStrategy(final List<Direction> directions) {
        super(directions);
    }

    @Override
    public List<Position> createPath(final Position source, final Position target) {
        final int gapOfFileOrder = target.getFileOrder() - source.getFileOrder();
        final int gapOfRankOrder = target.getRankOrder() - source.getRankOrder();

        final Direction findDirection = directions.stream()
                .filter(direction -> isReachable(direction, gapOfFileOrder, gapOfRankOrder))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("행마법 상 이동할 수 없는 위치입니다."));

        return getPath(source, target, findDirection);
    }

    private boolean isReachable(final Direction direction, final int gapOfFileOrder, final int gapOfRankOrder) {
        final int biggerNumber = Math.max(Math.abs(gapOfFileOrder), Math.abs(gapOfRankOrder));

        return direction.getFile() * biggerNumber == gapOfFileOrder &&
                direction.getRank() * biggerNumber == gapOfRankOrder;
    }

    private List<Position> getPath(final Position source, final Position target, final Direction findDirection) {
        List<Position> path = new ArrayList<>();

        Position currentPosition = move(source, findDirection);

        while (!currentPosition.equals(target)) {
            path.add(currentPosition);
            currentPosition = move(currentPosition, findDirection);
        }

        return path;
    }

    private Position move(final Position currentPosition, final Direction direction) {
        return Position.of(File.of(currentPosition.getFileOrder() + direction.getFile()),
                Rank.of(currentPosition.getRankOrder() + direction.getRank()));
    }
}
