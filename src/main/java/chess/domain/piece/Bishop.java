package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

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
    public List<Position> createPath(final Position source, final Position target,
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
