package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;

public class Knight extends UnslidingPiece {
    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public Set<Square> movableSquares(final Square currentSquare) {
        return null;
    }
}
