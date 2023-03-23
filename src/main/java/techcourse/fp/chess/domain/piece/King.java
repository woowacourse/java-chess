package techcourse.fp.chess.domain.piece;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.NoneSlidingStrategy;

public final class King extends OrdinaryPiece {

    public King(final Color color, final techcourse.fp.chess.domain.movingStrategy.MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static King create(final Color color) {
        return new King(color, new NoneSlidingStrategy(Direction.ofKing()));
    }
}
