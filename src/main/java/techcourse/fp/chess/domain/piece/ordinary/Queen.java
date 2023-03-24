package techcourse.fp.chess.domain.piece.ordinary;

import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.movingStrategy.SlidingStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.PieceType;

public final class Queen extends OrdinaryPiece {

    private Queen(final Color color, final PieceType pieceType,
                 final MovingStrategy movingStrategy) {
        super(color, pieceType, movingStrategy);
    }

    public static Queen create(final Color color) {
        return new Queen(color, PieceType.QUEEN, new SlidingStrategy(Direction.ofQueen()));
    }
}

