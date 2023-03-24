package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.NoneSlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.PieceType;

public final class King extends OrdinaryPiece {

    private King(final Color color, final PieceType pieceType,
                final MovingStrategy movingStrategy) {
        super(color, pieceType, movingStrategy);
    }

    public static King create(final Color color) {
        return new King(color, PieceType.KING,new NoneSlidingStrategy(Direction.ofKing()));
    }
}
