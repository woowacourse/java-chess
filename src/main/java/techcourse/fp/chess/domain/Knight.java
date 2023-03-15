package techcourse.fp.chess.domain;

import java.util.List;
import techcourse.fp.movingStrategy.DownTwoLeft;
import techcourse.fp.movingStrategy.DownTwoRight;
import techcourse.fp.movingStrategy.LeftTwoDownStrategy;
import techcourse.fp.movingStrategy.LeftTwoUpStrategy;
import techcourse.fp.movingStrategy.MovingStrategies;
import techcourse.fp.movingStrategy.MovingStrategy;
import techcourse.fp.movingStrategy.RightTwoDownStrategy;
import techcourse.fp.movingStrategy.RightTwoUpStrategy;
import techcourse.fp.movingStrategy.UpTwoLeftStrategy;
import techcourse.fp.movingStrategy.UpTwoRightStrategy;

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
    public List<Position> findPath(final Position sourcePosition, final Position targetPosition,
                                   final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(sourcePosition, targetPosition);

        if (targetColor.isSameColor(this.color)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        return List.of(movingStrategy.move(sourcePosition));
    }
}
