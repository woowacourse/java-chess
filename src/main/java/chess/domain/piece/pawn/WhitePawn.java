package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Team;

import java.util.List;

public final class WhitePawn extends Pawn {

    private WhitePawn(final Team team, final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(team, movingStrategies, attackStrategies);
    }

    public static WhitePawn create() {
        final List<MovingStrategy> movingStrategies = List.of(MoveUp.instance(), MoveLeftUp.instance(), MoveRightUp.instance());
        final List<MovingStrategy> attackStrategies = List.of(MoveLeftUp.instance(), MoveRightUp.instance());

        return new WhitePawn(Team.WHITE, new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
