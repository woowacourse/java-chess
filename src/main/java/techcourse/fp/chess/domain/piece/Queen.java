package techcourse.fp.chess.domain.piece;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;

public final class Queen extends OrdinaryPiece {

    public Queen(final Color color, final techcourse.fp.chess.domain.movingStrategy.MovingStrategy movingStrategy) {
        super(color, movingStrategy);
    }

    public static Queen create(final Color color) {
        return new Queen(color, new SlidingStrategy(Direction.ofQueen()));
    }
}
