package chess.domain.piece;

import chess.domain.Movement;

public class King extends Piece {

    public King(final Color color) {
        super(color, Type.KING);
    }

    @Override
    public boolean canMove(final Movement movement) {
        return (movement.isStraight() || movement.isDiagonal()) && movement.findDistance() == 1;
    }
}
