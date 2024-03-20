package chess.domain.piece;

import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;

public class King extends UnslidingPiece {

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    @Override
    public Set<Square> movableSquares(final Square source) {
        return null;
    }
}
