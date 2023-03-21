package chess.domain.piece;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeft;
import chess.domain.movingStrategy.MoveRight;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class Rook extends SlidingPiece {

    private Rook(final Color color, final MovingStrategies strategies) {
        super(color, PieceType.ROOK, strategies);
    }

    public static Rook create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                new MoveUp(), new MoveDown(),
                new MoveLeft(), new MoveRight());

        MovingStrategies strategies = new MovingStrategies(rawStrategies);
        return new Rook(color, strategies);
    }
}
