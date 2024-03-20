package chess.domain.piece;

import chess.domain.attribute.Color;

public class King extends UnslidingPiece {

    public King(final Color color) {
        super(color, PieceType.KING);
    }
}
