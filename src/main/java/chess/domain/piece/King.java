package chess.domain.piece;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeft;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRight;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class King extends NonSlidingPiece {

    private King(final Color color, final MovingStrategies strategies) {
        super(color, strategies, PieceType.KING);
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
}
