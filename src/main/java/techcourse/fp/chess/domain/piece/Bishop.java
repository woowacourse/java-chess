package techcourse.fp.chess.domain.piece;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;

public final class Bishop extends OrdinaryPiece {

    public Bishop(final Color color, final techcourse.fp.chess.domain.movingStrategy.MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static Bishop create(final Color color) {
        return new Bishop(color, new SlidingStrategy(Direction.ofBishop()));
    }
}
