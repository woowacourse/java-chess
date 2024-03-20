package chess.domain.piece;

import chess.domain.attribute.Color;

public class Rook extends SlidingPiece {
    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }
}
