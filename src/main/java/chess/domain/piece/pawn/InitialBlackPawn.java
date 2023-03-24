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
    private static final List<MovingStrategy> movingStrategies = List.of(MoveDownDown.instance(), MoveDown.instance(), MoveLeftDown.instance(), MoveRightDown.instance());
    private static final List<MovingStrategy> attackStrategies = List.of(MoveLeftDown.instance(), MoveRightDown.instance());
    private static final InitialBlackPawn INSTANCE = new InitialBlackPawn(new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));

    private InitialBlackPawn(final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(Team.BLACK, movingStrategies, attackStrategies);
    }

    public static InitialBlackPawn instance() {
        return INSTANCE;
    }

    @Override
    public boolean isInitialPawn() {
        return true;
    }
}
