package domain.piece;

import domain.board.Position;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            return false;
        }
        return sourcePosition.isOnSameDiagonalAs(targetPosition);
    }
}
