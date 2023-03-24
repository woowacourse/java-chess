package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveDownDown;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Team;

import java.util.List;

public final class InitialBlackPawn extends Pawn {

    private InitialBlackPawn(final Team team, final MovingStrategies movingStrategies) {
        super(team, movingStrategies);
    }

    public static InitialBlackPawn create() {
        final List<MovingStrategy> movingStrategies = List.of(
                MoveDownDown.instance(), MoveDown.instance(), MoveLeftDown.instance(), MoveRightDown.instance());
        return new InitialBlackPawn(Team.BLACK, new MovingStrategies(movingStrategies));
    }

    @Override
    public boolean isInitialPawn() {
        return true;
    }
}
