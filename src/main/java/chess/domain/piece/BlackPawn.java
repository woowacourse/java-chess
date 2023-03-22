package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public class BlackPawn extends Pawn {

    private BlackPawn(final Color color, final MovingStrategies movingStrategies) {
        super(color, movingStrategies);
    }

    public static BlackPawn create() {
        final List<MovingStrategy> movingStrategies = List.of(
                new MoveDown(), new MoveLeftDown(), new MoveRightDown());
        return new BlackPawn(Color.BLACK, new MovingStrategies(movingStrategies));
    }

    @Override
    public List<Position> createPath(final Position source, final Position target, final MovingStrategy strategy) {
        return null;
    }
}
