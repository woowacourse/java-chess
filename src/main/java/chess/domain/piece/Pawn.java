package chess.domain.piece;

import chess.domain.board.Position;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        int rankDifference = targetPosition.calculateRankDifference(sourcePosition);
        int fileDifference = targetPosition.calculateFileDifference(sourcePosition);

        if (isWhite()) {
            return (sourcePosition.isWhitePawnInitialPosition() && rankDifference == 2)
                || (rankDifference == 1 && Math.abs(fileDifference) <= 1);
        }
        return (sourcePosition.isBlackPawnInitialPosition() && rankDifference == -2)
            || (rankDifference == -1 && Math.abs(fileDifference) <= 1);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean exists() {
        return true;
    }
}
