package domain.piece;

import domain.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            return false;
        }
        int fileDifference = sourcePosition.calculateFileDifference(targetPosition);
        int rankDifference = sourcePosition.calculateRankDifference(targetPosition);
        return fileDifference == 0 || rankDifference == 0;
    }
}
