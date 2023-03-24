package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.Team;

import java.util.List;

public final class BlackPawn extends Pawn {
    private static final List<MovingStrategy> movingStrategies = List.of(MoveDown.instance(), MoveLeftDown.instance(), MoveRightDown.instance());
    private static final List<MovingStrategy> attackStrategies = List.of(MoveLeftDown.instance(), MoveRightDown.instance());
    private static final BlackPawn INSTANCE = new BlackPawn(new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));


    private BlackPawn(final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(Team.BLACK, movingStrategies, attackStrategies);
    }

    public static BlackPawn instance() {
        return INSTANCE;
    }

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
