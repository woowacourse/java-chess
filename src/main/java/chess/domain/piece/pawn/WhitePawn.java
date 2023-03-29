package chess.domain.piece.pawn;

import chess.domain.movingstrategy.MoveLeftUp;
import chess.domain.movingstrategy.MoveRightUp;
import chess.domain.movingstrategy.MoveUp;
import chess.domain.movingstrategy.MovingStrategies;
import chess.domain.movingstrategy.MovingStrategy;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.List;

public final class WhitePawn extends Pawn {

    private static final List<MovingStrategy> movingStrategies = List.of(MoveUp.instance(), MoveLeftUp.instance(), MoveRightUp.instance());
    private static final List<MovingStrategy> attackStrategies = List.of(MoveLeftUp.instance(), MoveRightUp.instance());
    private static final WhitePawn INSTANCE = new WhitePawn(new MovingStrategies(movingStrategies), new AttackStrategies(attackStrategies));

    private WhitePawn(final MovingStrategies movingStrategies, final AttackStrategies attackStrategies) {
        super(Team.WHITE, PieceType.WHITE_PAWN, movingStrategies, attackStrategies);
    }

    public static WhitePawn instance() {
        return INSTANCE;
    }
}
