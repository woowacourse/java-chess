package domain.piece;

import domain.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            return false;
        }
        int rankDifference = sourcePosition.calculateRankDifference(targetPosition);
        int fileDifference = sourcePosition.calculateFileDifference(targetPosition);
        return Math.abs(rankDifference) == Math.abs(fileDifference)
            || rankDifference == 0
            || fileDifference == 0;
    }
}
