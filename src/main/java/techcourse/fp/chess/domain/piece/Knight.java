package techcourse.fp.chess.domain.piece;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.NoneSlidingStrategy;

public final class Knight extends OrdinaryPiece {

    public Knight(final Color color, final techcourse.fp.chess.domain.movingStrategy.MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static Knight create(final Color color) {
        return new Knight(color, new NoneSlidingStrategy(Direction.ofKnight()));
    }
}

