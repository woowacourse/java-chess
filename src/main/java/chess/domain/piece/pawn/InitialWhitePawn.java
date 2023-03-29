package chess.domain.piece.pawn;

import chess.domain.movingstrategy.MoveLeftUp;
import chess.domain.movingstrategy.MoveRightUp;
import chess.domain.movingstrategy.MoveUp;
import chess.domain.movingstrategy.MoveUpUp;
import chess.domain.movingstrategy.MovingStrategies;
import chess.domain.movingstrategy.MovingStrategy;
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
}
