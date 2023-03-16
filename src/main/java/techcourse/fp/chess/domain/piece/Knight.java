package techcourse.fp.chess.domain.piece;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Color;
import techcourse.fp.chess.domain.Position;
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

public final class Knight extends MovablePiece {

    private Knight(final Color color, final MovingStrategies strategies) {
        super(color, strategies);
    }

    public static Knight create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                UpTwoRightStrategy.create(), UpTwoLeftStrategy.create(),
                RightTwoUpStrategy.create(), RightTwoDownStrategy.create(),
                DownTwoRight.create(), DownTwoLeft.create(),
                LeftTwoDownStrategy.create(), LeftTwoUpStrategy.create());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new Knight(color, strategies);
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
