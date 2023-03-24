package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.NoneSlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.PieceType;

public final class Knight extends OrdinaryPiece {

    private Knight(final Color color, final PieceType pieceType,
                  final MovingStrategy movingStrategy) {
        super(color, pieceType, movingStrategy);
    }

    public static Knight create(final Color color) {
        return new Knight(color, PieceType.KNIGHT,new NoneSlidingStrategy(Direction.ofKnight()));
    }
}

