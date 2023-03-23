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

public final class Queen extends SlidingPiece {

    private Queen(final Color color, final MovingStrategies strategies) {
        super(color, PieceType.QUEEN, strategies);
    }

    public static Queen create(final Color color) {
        final List<MovingStrategy> rawStrategies = List.of(
                MoveRightUp.get(), MoveRightDown.get(), MoveLeftDown.get(), MoveLeftUp.get(),
                MoveUp.get(), MoveDown.get(), MoveLeft.get(), MoveRight.get());

        MovingStrategies strategies = new MovingStrategies(rawStrategies);
        return new Queen(color, strategies);
    }
}
