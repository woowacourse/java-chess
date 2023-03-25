package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.NoneSlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;

public final class Knight extends OrdinaryPiece {


    private Knight(final Color color, final MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static Knight create(final Color color) {
        return new Knight(color, new NoneSlidingStrategy(Direction.ofKnight()));
    }
}

