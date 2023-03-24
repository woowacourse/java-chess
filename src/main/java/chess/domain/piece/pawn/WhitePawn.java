package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Team;

import java.util.List;

public final class WhitePawn extends Pawn {

    private static final List<MovingStrategy> movingStrategies = List.of(MoveUp.instance(), MoveLeftUp.instance(), MoveRightUp.instance());
    private static final List<MovingStrategy> attackStrategies = List.of(MoveLeftUp.instance(), MoveRightUp.instance());
    private static final WhitePawn INSTANCE = new WhitePawn(new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));

    private WhitePawn(final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(Team.WHITE, movingStrategies, attackStrategies);
    }

    public static WhitePawn instance() {
        return INSTANCE;
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
