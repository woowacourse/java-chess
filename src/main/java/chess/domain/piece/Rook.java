package chess.domain.piece;

import chess.domain.Movement;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color, Type.ROOK);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return movement.isStraight();
    }
}
