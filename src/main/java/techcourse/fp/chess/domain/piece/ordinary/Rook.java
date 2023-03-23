package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;

public final class Rook extends OrdinaryPiece {

    private Rook(final Color color, final MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static Rook create(final Color color) {
        return new Rook(color, new SlidingStrategy(Direction.ofRook()));
    }
}
