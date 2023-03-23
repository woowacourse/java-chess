package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.NoneSlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;

public final class Knight extends OrdinaryPiece {

    public Knight(final Color color, final techcourse.fp.chess.domain.movingStrategy.MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static Knight create(final Color color) {
        return new Knight(color, new NoneSlidingStrategy(Direction.ofKnight()));
    }
}

