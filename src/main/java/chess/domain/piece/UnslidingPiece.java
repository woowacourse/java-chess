package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public abstract class UnslidingPiece extends Piece {
    protected UnslidingPiece(final Color color, final PieceType pieceType) {
        super(color, pieceType);
    }
}
