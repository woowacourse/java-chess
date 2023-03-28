package chess.domain.piece;

import chess.domain.movingstrategy.MoveDown;
import chess.domain.movingstrategy.MoveLeft;
import chess.domain.movingstrategy.MoveRight;
import chess.domain.movingstrategy.MoveUp;
import chess.domain.movingstrategy.MovingStrategies;
import chess.domain.movingstrategy.MovingStrategy;

import java.util.List;

public final class Rook extends SlidingPiece {

    private static final List<MovingStrategy> strategies = List.of(
            MoveUp.instance(), MoveDown.instance(), MoveLeft.instance(), MoveRight.instance());
    private static final Rook BLACK = new Rook(Team.BLACK, new MovingStrategies(strategies));
    private static final Rook WHITE = new Rook(Team.WHITE, new MovingStrategies(strategies));


    private Rook(final Team team, final MovingStrategies strategies) {
        super(team, PieceType.ROOK, strategies);
    }

    public static Rook instance(final Team team) {
        if (team.isBlack()) {
            return BLACK;
        }
        if (team.isWhite()) {
            return WHITE;
        }
        throw new AssertionError();
    }
}
