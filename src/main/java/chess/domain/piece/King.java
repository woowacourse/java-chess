package chess.domain.piece;

import chess.domain.piece.position.PiecePosition;

public class King extends Piece {

    protected King(final PiecePosition position, final Color color) {
        super(position, color);
    }
}
