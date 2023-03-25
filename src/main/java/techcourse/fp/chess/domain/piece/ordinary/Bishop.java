package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;

public final class Bishop extends OrdinaryPiece {

    private Bishop(final Color color, final MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static Bishop create(final Color color) {
        return new Bishop(color, new SlidingStrategy(Direction.ofBishop()));
    }
}
