package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Color;

import java.util.List;

public final class BlackPawn extends Pawn {

    private BlackPawn(final Color color, final MovingStrategies movingStrategies) {
        super(color, movingStrategies);
    }

    public static BlackPawn create() {
        final List<MovingStrategy> movingStrategies = List.of(
                new MoveDown(), new MoveLeftDown(), new MoveRightDown());
        return new BlackPawn(Color.BLACK, new MovingStrategies(movingStrategies));
    }

    @Override
    public boolean isInitialPosition() {
        return false;
    }

}
