package chess.domain.piece;

import chess.domain.piece.position.PiecePosition;

public class Pawn extends Piece {

    protected Pawn(final PiecePosition position, final Color color) {
        super(position, color);
    }
}
