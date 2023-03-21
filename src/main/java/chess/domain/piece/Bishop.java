package chess.domain.piece;

import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class Bishop extends SlidingPiece {

    private Bishop(final Color color, final MovingStrategies strategies) {
        super(color, PieceType.BISHOP, strategies);
    }

    public static Bishop create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                new MoveRightUp(), new MoveRightDown(),
                new MoveLeftDown(), new MoveLeftUp());

        MovingStrategies strategies = new MovingStrategies(rawStrategies);
        return new Bishop(color, strategies);
    }
}
