package techcourse.fp.chess.domain;

import java.util.Collections;
import java.util.List;
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

public class King extends Piece {

    {
        final List<MovingStrategy> rawStrategies = List.of(
                new RightUpStrategy(), new RightDownStrategy(),
                new LeftDownStrategy(), new LeftUpStrategy(),
                new UpStrategy(), new DownStrategy(),
                new LeftStrategy(), new RightStrategy());
        strategies = new MovingStrategies(rawStrategies);
    }

    public King(final Color color) {
        super(color);
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
        final Position moveResult = movingStrategy.move(source);

        if (!moveResult.equals(target)) {
            throw new IllegalArgumentException("킹은 한 칸만 이동 가능합니다.");
        }

        return Collections.emptyList();
    }
}
