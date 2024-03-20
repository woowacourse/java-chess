package chess.domain.piece;

import chess.domain.attribute.Color;

public abstract class SlidingPiece extends Piece {
    protected SlidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }
}
