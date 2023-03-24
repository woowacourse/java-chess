package chess.domain.piece;

import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class Bishop extends SlidingPiece {

    private static final List<MovingStrategy> strategies = List.of(
            MoveRightUp.instance(), MoveRightDown.instance(), MoveLeftDown.instance(), MoveLeftUp.instance());
    private static final Bishop BLACK = new Bishop(Team.BLACK, new MovingStrategies(strategies));
    private static final Bishop WHITE = new Bishop(Team.WHITE, new MovingStrategies(strategies));

    private Bishop(final Team team, final MovingStrategies strategies) {
        super(team, PieceType.BISHOP, strategies);
    }

    public static Bishop instance(final Team team) {
        if (team.isBlack()) {
            return BLACK;
        }
        if (team.isWhite()) {
            return WHITE;
        }
        throw new AssertionError();
    }
}
