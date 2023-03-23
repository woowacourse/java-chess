package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.NoneSlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;

public final class King extends OrdinaryPiece {

    private King(final Color color, final MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static King create(final Color color) {
        return new King(color, new NoneSlidingStrategy(Direction.ofKing()));
    }
}
