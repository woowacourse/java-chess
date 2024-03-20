package chess.domain.piece;

import chess.domain.attribute.Color;

public class Queen extends SlidingPiece {
    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }
}
