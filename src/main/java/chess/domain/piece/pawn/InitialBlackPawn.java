package chess.domain.piece.pawn;

import chess.domain.movingstrategy.MoveDown;
import chess.domain.movingstrategy.MoveDownDown;
import chess.domain.movingstrategy.MoveLeftDown;
import chess.domain.movingstrategy.MoveRightDown;
import chess.domain.movingstrategy.MovingStrategies;
import chess.domain.movingstrategy.MovingStrategy;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.List;

public final class InitialBlackPawn extends Pawn {
    private static final List<MovingStrategy> movingStrategies = List.of(MoveDownDown.instance(), MoveDown.instance(), MoveLeftDown.instance(), MoveRightDown.instance());
    private static final List<MovingStrategy> attackStrategies = List.of(MoveLeftDown.instance(), MoveRightDown.instance());
    private static final InitialBlackPawn INSTANCE = new InitialBlackPawn(new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));

    private InitialBlackPawn(final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(Team.BLACK, PieceType.INITIAL_BLACK_PAWN, movingStrategies, attackStrategies);
    }

    public static InitialBlackPawn instance() {
        return INSTANCE;
    }

    @Override
    public boolean isInitialPawn() {
        return true;
    }
}
