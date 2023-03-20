package techcourse.fp.chess.domain.piece;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.movingStrategy.MoveDown;
import techcourse.fp.chess.movingStrategy.MoveLeft;
import techcourse.fp.chess.movingStrategy.MoveLeftDown;
import techcourse.fp.chess.movingStrategy.MoveLeftUp;
import techcourse.fp.chess.movingStrategy.MoveRight;
import techcourse.fp.chess.movingStrategy.MoveRightDown;
import techcourse.fp.chess.movingStrategy.MoveRightUp;
import techcourse.fp.chess.movingStrategy.MoveUp;
import techcourse.fp.chess.movingStrategy.MovingStrategies;
import techcourse.fp.chess.movingStrategy.MovingStrategy;

public final class King extends MovablePiece {

    private King(final Color color, final MovingStrategies strategies) {
        super(color, strategies);
    }

    public static King create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                new MoveRightUp(), new MoveRightDown(),
                new MoveLeftDown(), new MoveLeftUp(),
                new MoveUp(), new MoveDown(),
                new MoveLeft(), new MoveRight());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new King(color, strategies);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target,
                                   final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(source, target);
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
