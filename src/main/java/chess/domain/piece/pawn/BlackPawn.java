package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Team;

import java.util.List;

public final class BlackPawn extends Pawn {

    private BlackPawn(final Team team, final MovingStrategies movingStrategies) {
        super(team, movingStrategies);
    }

    public static BlackPawn create() {
        final List<MovingStrategy> movingStrategies = List.of(
                MoveDown.instance(), MoveLeftDown.instance(), MoveRightDown.instance());
        return new BlackPawn(Team.BLACK, new MovingStrategies(movingStrategies));
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
