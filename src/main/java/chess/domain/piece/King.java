package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        int fileDifference = sourcePosition.calculateFileDifference(targetPosition);
        int rankDifference = sourcePosition.calculateRankDifference(targetPosition);
        return Math.abs(fileDifference) == 1 && Math.abs(rankDifference) <= 1
            || Math.abs(fileDifference) <= 1 && Math.abs(rankDifference) == 1;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean exists() {
        return true;
    }
}
