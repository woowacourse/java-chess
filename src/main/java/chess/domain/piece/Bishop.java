package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;

public class Bishop extends SlidingPiece {

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public Set<Square> movableSquares(final Square currentSquare) {
        return null;
    }
}
