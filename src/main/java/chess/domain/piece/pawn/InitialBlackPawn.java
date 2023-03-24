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

    private InitialBlackPawn(final Team team, final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(team, movingStrategies, attackStrategies);
    }

    public static InitialBlackPawn create() {
        final List<MovingStrategy> movingStrategies = List.of(MoveDownDown.instance(), MoveDown.instance(), MoveLeftDown.instance(), MoveRightDown.instance());
        final List<MovingStrategy> attackStrategies = List.of(MoveLeftDown.instance(), MoveRightDown.instance());

        return new InitialBlackPawn(Team.BLACK, new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));
    }

    @Override
    public boolean isInitialPawn() {
        return true;
    }
}
