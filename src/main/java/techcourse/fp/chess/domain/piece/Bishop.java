package techcourse.fp.chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.movingStrategy.MoveLeftDown;
import techcourse.fp.chess.movingStrategy.MoveLeftUp;
import techcourse.fp.chess.movingStrategy.MoveRightDown;
import techcourse.fp.chess.movingStrategy.MoveRightUp;
import techcourse.fp.chess.movingStrategy.MovingStrategies;
import techcourse.fp.chess.movingStrategy.MovingStrategy;

public final class Bishop extends MovablePiece {

    private Bishop(final Color color, final MovingStrategies strategies) {
        super(color, strategies);
    }

    public static Bishop create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                new MoveRightUp(), new MoveRightDown(),
                new MoveLeftDown(), new MoveLeftUp());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new Bishop(color, strategies);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target,
                                   final Color targetColor) {
        final MovingStrategy movingStrategy = strategies.findStrategy(source, target);
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
