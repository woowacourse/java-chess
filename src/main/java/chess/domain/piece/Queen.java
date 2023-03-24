package chess.domain.piece;

import chess.domain.movingStrategy.MoveDown;
import chess.domain.movingStrategy.MoveLeft;
import chess.domain.movingStrategy.MoveLeftDown;
import chess.domain.movingStrategy.MoveLeftUp;
import chess.domain.movingStrategy.MoveRight;
import chess.domain.movingStrategy.MoveRightDown;
import chess.domain.movingStrategy.MoveRightUp;
import chess.domain.movingStrategy.MoveUp;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.List;

public final class Queen extends SlidingPiece {

    private static final List<MovingStrategy> strategies = List.of(
            MoveRightUp.instance(), MoveRightDown.instance(), MoveLeftDown.instance(), MoveLeftUp.instance(),
            MoveUp.instance(), MoveDown.instance(), MoveLeft.instance(), MoveRight.instance());
    private static final Queen BLACK = new Queen(Team.BLACK, new MovingStrategies(strategies));
    private static final Queen WHITE = new Queen(Team.WHITE, new MovingStrategies(strategies));

    private Queen(final Team team, final MovingStrategies strategies) {
        super(team, PieceType.QUEEN, strategies);
    }

    public static Queen instance(final Team team) {
        if (team.isBlack()) {
            return BLACK;
        }
        if (team.isWhite()) {
            return WHITE;
        }
        throw new AssertionError();
    }
}
