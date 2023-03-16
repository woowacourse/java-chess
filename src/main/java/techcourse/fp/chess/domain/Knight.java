package techcourse.fp.chess.domain;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.movingStrategy.DownTwoLeft;
import techcourse.fp.chess.movingStrategy.DownTwoRight;
import techcourse.fp.chess.movingStrategy.LeftTwoDownStrategy;
import techcourse.fp.chess.movingStrategy.LeftTwoUpStrategy;
import techcourse.fp.chess.movingStrategy.MovingStrategies;
import techcourse.fp.chess.movingStrategy.MovingStrategy;
import techcourse.fp.chess.movingStrategy.RightTwoDownStrategy;
import techcourse.fp.chess.movingStrategy.RightTwoUpStrategy;
import techcourse.fp.chess.movingStrategy.UpTwoLeftStrategy;
import techcourse.fp.chess.movingStrategy.UpTwoRightStrategy;

public class Knight extends Piece {

    {
        final List<MovingStrategy> rawStrategies = List.of(
                UpTwoRightStrategy.create(), UpTwoLeftStrategy.create(),
                RightTwoUpStrategy.create(), RightTwoDownStrategy.create(),
                DownTwoRight.create(), DownTwoLeft.create(),
                LeftTwoDownStrategy.create(), LeftTwoUpStrategy.create());
        strategies = new MovingStrategies(rawStrategies);
    }

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target,
                                   final Color targetColor) {
        strategies.findStrategy(source, target);

        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        return Collections.emptyList();
    }
}
