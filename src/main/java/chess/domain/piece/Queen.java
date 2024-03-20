package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;

public class Queen extends SlidingPiece {
    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public Set<Square> movableSquares(final Square currentSquare) {
        return null;
    }
}
