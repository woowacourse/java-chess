package chess.domain.piece;

import chess.domain.Position;

public class Queen extends Piece {

    private static final double QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(color, QUEEN_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return isSameRowOrCol(fromPosition, toPosition) || isDiagonal(fromPosition, toPosition);
    }

    private boolean isSameRowOrCol(Position fromPosition, Position toPosition) {
        return fromPosition.isSameFile(toPosition) || fromPosition.isSameRank(toPosition);
    }

    private boolean isDiagonal(Position fromPosition, Position toPosition) {
        int height = fromPosition.getRankDifference(toPosition);
        int width = fromPosition.getFileDifference(toPosition);
        return Math.pow(height, 2) == Math.pow(width, 2);
    }
}
