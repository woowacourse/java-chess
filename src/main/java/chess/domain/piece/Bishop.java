package chess.domain.piece;

import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class Bishop extends SlidingPiece {

    private Bishop(final Team team, final MovingStrategies strategies) {
        super(team, PieceType.BISHOP, strategies);
    }

    public static Bishop create(final Team team) {
        final List<MovingStrategy> rawStrategies = List.of(
                MoveRightUp.instance(), MoveRightDown.instance(), MoveLeftDown.instance(), MoveLeftUp.instance());

        MovingStrategies strategies = new MovingStrategies(rawStrategies);
        return new Bishop(team, strategies);
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
