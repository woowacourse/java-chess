package domain.piece.piecerole;

import domain.position.Position;

public abstract class Pawn implements PieceRole {
    @Override
    public boolean canMove(final Position sourcePosition, final Position targetPosition) {
        return true;
    }
}
