package techcourse.fp.chess.domain.piece;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.movingStrategy.MoveDownTwoLeft;
import techcourse.fp.chess.movingStrategy.MoveDownTwoRight;
import techcourse.fp.chess.movingStrategy.MoveLeftTwoDown;
import techcourse.fp.chess.movingStrategy.MoveLeftTwoUp;
import techcourse.fp.chess.movingStrategy.MoveRightTwoDown;
import techcourse.fp.chess.movingStrategy.MoveRightTwoUp;
import techcourse.fp.chess.movingStrategy.MoveUpTwoLeft;
import techcourse.fp.chess.movingStrategy.MoveUpTwoRight;
import techcourse.fp.chess.movingStrategy.MovingStrategies;
import techcourse.fp.chess.movingStrategy.MovingStrategy;

public final class Knight extends MovablePiece {

    private Knight(final Color color, final MovingStrategies strategies) {
        super(color, strategies);
    }

    public static Knight create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                MoveUpTwoRight.create(), MoveUpTwoLeft.create(),
                MoveRightTwoUp.create(), MoveRightTwoDown.create(),
                MoveDownTwoRight.create(), MoveDownTwoLeft.create(),
                MoveLeftTwoDown.create(), MoveLeftTwoUp.create());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new Knight(color, strategies);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target,
                                   final Color targetColor) {
        strategies.findStrategy(source, target);
        return Collections.emptyList();
    }
}
