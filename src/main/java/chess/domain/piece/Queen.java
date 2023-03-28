package chess.domain.piece;

import chess.domain.movingstrategy.MoveDown;
import chess.domain.movingstrategy.MoveLeft;
import chess.domain.movingstrategy.MoveLeftDown;
import chess.domain.movingstrategy.MoveLeftUp;
import chess.domain.movingstrategy.MoveRight;
import chess.domain.movingstrategy.MoveRightDown;
import chess.domain.movingstrategy.MoveRightUp;
import chess.domain.movingstrategy.MoveUp;
import chess.domain.movingstrategy.MovingStrategies;
import chess.domain.movingstrategy.MovingStrategy;

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
