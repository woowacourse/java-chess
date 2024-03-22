package chess.domain.piece;

import chess.domain.board.Position;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        int rankDifference = sourcePosition.calculateRankDifference(targetPosition);
        int fileDifference = sourcePosition.calculateFileDifference(targetPosition);
        return Math.abs(rankDifference) <= 1 && Math.abs(fileDifference) <= 1;
    }
}
