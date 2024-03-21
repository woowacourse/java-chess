package chess.domain.piece;

import chess.domain.Movement;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return movement.isDiagonal();
    }
}
