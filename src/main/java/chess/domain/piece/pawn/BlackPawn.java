package chess.domain.piece.pawn;

import chess.domain.movingstrategy.MoveDown;
import chess.domain.movingstrategy.MoveLeftDown;
import chess.domain.movingstrategy.MoveRightDown;
import chess.domain.movingstrategy.MovingStrategies;
import chess.domain.movingstrategy.MovingStrategy;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.List;

public final class BlackPawn extends Pawn {
    private static final List<MovingStrategy> movingStrategies = List.of(MoveDown.instance(), MoveLeftDown.instance(), MoveRightDown.instance());
    private static final List<MovingStrategy> attackStrategies = List.of(MoveLeftDown.instance(), MoveRightDown.instance());
    private static final BlackPawn INSTANCE = new BlackPawn(new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));

    private BlackPawn(final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(Team.BLACK, PieceType.BLACK_PAWN, movingStrategies, attackStrategies);
    }

    public static BlackPawn instance() {
        return INSTANCE;
    }
}
