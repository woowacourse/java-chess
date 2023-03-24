package chess.domain.piece;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeft;
import chess.domain.movingStrategy.MoveRight;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class Rook extends SlidingPiece {

    private Rook(final Team team, final MovingStrategies strategies) {
        super(team, PieceType.ROOK, strategies);
    }

    public static Rook create(final Team team) {
        final List<MovingStrategy> rawStrategies = List.of(MoveUp.instance(), MoveDown.instance(), MoveLeft.instance(), MoveRight.instance());

        MovingStrategies strategies = new MovingStrategies(rawStrategies);
        return new Rook(team, strategies);
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
