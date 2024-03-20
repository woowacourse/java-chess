package chess.domain.piece;

import chess.domain.attribute.Color;

public class Knight extends UnslidingPiece {
    public Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }
}
