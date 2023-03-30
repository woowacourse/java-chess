package chess.domain.piece;

import chess.domain.movingstrategy.MoveDownTwoLeft;
import chess.domain.movingstrategy.MoveDownTwoRight;
import chess.domain.movingstrategy.MoveLeftTwoDown;
import chess.domain.movingstrategy.MoveLeftTwoUp;
import chess.domain.movingstrategy.MoveRightTwoDown;
import chess.domain.movingstrategy.MoveRightTwoUp;
import chess.domain.movingstrategy.MoveUpTwoLeft;
import chess.domain.movingstrategy.MoveUpTwoRight;
import chess.domain.movingstrategy.MovingStrategies;
import chess.domain.movingstrategy.MovingStrategy;

import java.util.List;

public final class Knight extends NonSlidingPiece {

    private static final List<MovingStrategy> strategies = List.of(
            MoveUpTwoRight.instance(), MoveUpTwoLeft.instance(), MoveRightTwoUp.instance(), MoveRightTwoDown.instance(),
            MoveDownTwoRight.instance(), MoveDownTwoLeft.instance(), MoveLeftTwoDown.instance(), MoveLeftTwoUp.instance());
    private static final Knight BLACK = new Knight(Team.BLACK, new MovingStrategies(strategies));
    private static final Knight WHITE = new Knight(Team.WHITE, new MovingStrategies(strategies));

    private Knight(final Team team, final MovingStrategies strategies) {
        super(team, PieceType.KNIGHT, strategies);
    }

    public static Knight instance(final Team team) {
        if (team.isBlack()) {
            return BLACK;
        }
        if (team.isWhite()) {
            return WHITE;
        }
        throw new AssertionError();
    }
}
