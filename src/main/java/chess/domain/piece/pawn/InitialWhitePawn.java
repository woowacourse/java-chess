package chess.domain.piece.pawn;

import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MoveUpUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.List;

public final class InitialWhitePawn extends Pawn {

    private static final List<MovingStrategy> movingStrategies = List.of(MoveUpUp.instance(), MoveUp.instance(), MoveLeftUp.instance(), MoveRightUp.instance());
    private static final List<MovingStrategy> attackStrategies = List.of(MoveLeftUp.instance(), MoveRightUp.instance());
    private static final InitialWhitePawn INSTANCE = new InitialWhitePawn(new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));

    private InitialWhitePawn(final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(Team.WHITE, PieceType.INITIAL_WHITE_PAWN, movingStrategies, attackStrategies);
    }

    public static InitialWhitePawn instance() {
        return INSTANCE;
    }

    @Override
    public boolean isInitialPawn() {
        return true;
    }
}
