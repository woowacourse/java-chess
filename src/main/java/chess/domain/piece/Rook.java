package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;

public class Rook extends SlidingPiece {
    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public Set<Square> movableSquares(final Square currentSquare) {
        return null;
    }
}
