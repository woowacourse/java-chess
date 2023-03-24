package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.PieceType;

public final class Rook extends OrdinaryPiece {

    private Rook(final Color color, final PieceType pieceType,
                final MovingStrategy movingStrategy) {
        super(color, pieceType, movingStrategy);
    }

    public static Rook create(final Color color) {
        return new Rook(color, PieceType.ROOK,new SlidingStrategy(Direction.ofRook()));
    }
}
