package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.PieceType;

public final class Bishop extends OrdinaryPiece {

    private Bishop(final Color color, final PieceType pieceType,
                  final MovingStrategy movingStrategy) {
        super(color, pieceType, movingStrategy);
    }

    public static Bishop create(final Color color) {
        return new Bishop(color, PieceType.BISHOP,new SlidingStrategy(Direction.ofBishop()));
    }
}
