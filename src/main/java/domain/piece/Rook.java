package domain.piece;

import domain.board.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            return false;
        }
        return sourcePosition.isOnSameRankAs(targetPosition)
            || sourcePosition.isOnSameFileAs(targetPosition);
    }
}
