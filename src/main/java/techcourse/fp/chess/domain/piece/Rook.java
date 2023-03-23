package techcourse.fp.chess.domain.piece;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;

public final class Rook extends OrdinaryPiece {


    public Rook(final Color color, final techcourse.fp.chess.domain.movingStrategy.MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static Rook create(final Color color) {
        return new Rook(color, new SlidingStrategy(Direction.ofRook()));
    }
}
