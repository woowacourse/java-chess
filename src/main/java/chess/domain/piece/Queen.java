package chess.domain.piece;

import chess.domain.Position;
import chess.movingStrategy.MoveDown;
import chess.movingStrategy.MoveLeft;
import chess.movingStrategy.MoveLeftDown;
import chess.movingStrategy.MoveLeftUp;
import chess.movingStrategy.MoveRight;
import chess.movingStrategy.MoveRightDown;
import chess.movingStrategy.MoveRightUp;
import chess.movingStrategy.MoveUp;
import chess.movingStrategy.MovingStrategies;
import chess.movingStrategy.MovingStrategy;

import java.util.ArrayList;
import java.util.List;

public final class Queen extends MovablePiece {

    private Queen(final Color color, final MovingStrategies strategies) {
        super(color, strategies, PieceType.QUEEN);
    }

    public static Queen create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                new MoveRightUp(), new MoveRightDown(),
                new MoveLeftDown(), new MoveLeftUp(),
                new MoveUp(), new MoveDown(),
                new MoveLeft(), new MoveRight());
        MovingStrategies strategies = new MovingStrategies(rawStrategies);

        return new Queen(color, strategies);
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
