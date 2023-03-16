package techcourse.fp.chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import techcourse.fp.chess.domain.Color;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.movingStrategy.DownStrategy;
import techcourse.fp.chess.movingStrategy.LeftDownStrategy;
import techcourse.fp.chess.movingStrategy.LeftStrategy;
import techcourse.fp.chess.movingStrategy.LeftUpStrategy;
import techcourse.fp.chess.movingStrategy.MovingStrategies;
import techcourse.fp.chess.movingStrategy.MovingStrategy;
import techcourse.fp.chess.movingStrategy.RightDownStrategy;
import techcourse.fp.chess.movingStrategy.RightStrategy;
import techcourse.fp.chess.movingStrategy.RightUpStrategy;
import techcourse.fp.chess.movingStrategy.UpStrategy;

public final class Queen extends MovablePiece {

    private Queen(final Color color, final MovingStrategies strategies) {
        super(color, strategies);
    }

    public static Queen create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                new RightUpStrategy(), new RightDownStrategy(),
                new LeftDownStrategy(), new LeftUpStrategy(),
                new UpStrategy(), new DownStrategy(),
                new LeftStrategy(), new RightStrategy());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new Queen(color, strategies);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target,
                                   final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(source, target);

        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        return createPath(source, target, movingStrategy);
    }

    private List<Position> createPath(final Position source, final Position target,
                                      final MovingStrategy movingStrategy) {
        List<Position> path = new ArrayList<>();
        Position currentPosition = source;

        do {
            currentPosition = movingStrategy.move(currentPosition);
            path.add(currentPosition);
        } while (!currentPosition.isUpDown(target) && !currentPosition.isOnDiagonal(target));

        return path;
    }
}
