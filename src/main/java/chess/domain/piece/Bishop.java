package chess.domain.piece;

import chess.domain.Position;
import chess.movingStrategy.MoveLeftDown;
import chess.movingStrategy.MoveLeftUp;
import chess.movingStrategy.MoveRightDown;
import chess.movingStrategy.MoveRightUp;
import chess.movingStrategy.MovingStrategies;
import chess.movingStrategy.MovingStrategy;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends MovablePiece {

    private Bishop(final Color color, final MovingStrategies strategies) {
        super(color, strategies, PieceType.BISHOP);
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
