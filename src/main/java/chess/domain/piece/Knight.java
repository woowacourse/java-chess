package chess.domain.piece;

import chess.domain.movingStrategy.MoveDownTwoLeft;
import chess.domain.movingStrategy.MoveDownTwoRight;
import chess.domain.movingStrategy.MoveLeftTwoDown;
import chess.domain.movingStrategy.MoveLeftTwoUp;
import chess.domain.movingStrategy.MoveRightTwoDown;
import chess.domain.movingStrategy.MoveRightTwoUp;
import chess.domain.movingStrategy.MoveUpTwoLeft;
import chess.domain.movingStrategy.MoveUpTwoRight;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

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

    @Override
    public boolean isInitialPawn() {
        return false;
    }
}
