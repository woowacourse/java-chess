package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MoveUpUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Color;

import java.util.List;

public final class InitialWhitePawn extends Pawn {

    private InitialWhitePawn(final Color color, final MovingStrategies movingStrategies) {
        super(color, movingStrategies);
    }

    public static InitialWhitePawn create() {
        final List<MovingStrategy> movingStrategies = List.of(
                MoveUpUp.get(), MoveUp.get(), MoveLeftUp.get(), MoveRightUp.get());
        return new InitialWhitePawn(Color.WHITE, new MovingStrategies(movingStrategies));
    }

    @Override
    public boolean isInitialPawn() {
        return true;
    }
}
