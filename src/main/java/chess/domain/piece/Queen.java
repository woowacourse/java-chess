package chess.domain.piece;

import chess.domain.Movement;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color, Type.QUEEN);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        return movement.isCross() || movement.isDiagonal();
    }
}
