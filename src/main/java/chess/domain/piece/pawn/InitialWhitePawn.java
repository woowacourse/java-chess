package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MoveUpUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Team;

import java.util.List;

public final class InitialWhitePawn extends Pawn {

    private InitialWhitePawn(final Team team, final MovingStrategies movingStrategies) {
        super(team, movingStrategies);
    }

    public static InitialWhitePawn create() {
        final List<MovingStrategy> movingStrategies = List.of(
                MoveUpUp.instance(), MoveUp.instance(), MoveLeftUp.instance(), MoveRightUp.instance());
        return new InitialWhitePawn(Team.WHITE, new MovingStrategies(movingStrategies));
    }

    @Override
    public boolean isInitialPawn() {
        return true;
    }
}
