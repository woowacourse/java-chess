package chess.domain.piece;

import chess.domain.Position;

public class King extends Piece {

    private static final int SCOPE_DIFFERENCE = 1;
    private static final double KING_SCORE = 0;

    public King(Color color) {
        super(color, KING_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return isSameRowOrColAndOneDifference(fromPosition, toPosition)
            || isOneDifferenceDiagonal(fromPosition, toPosition);
    }

    private boolean isSameRowOrColAndOneDifference(Position fromPosition, Position toPosition) {
        return isSameColAndOneDifference(fromPosition, toPosition)
            || isSameRowAndOneDifference(fromPosition, toPosition);
    }

    private boolean isSameColAndOneDifference(Position fromPosition, Position toPosition) {
        return fromPosition.isSameFile(toPosition)
            && Math.abs(fromPosition.getRankDifference(toPosition)) == SCOPE_DIFFERENCE;
    }

    private boolean isSameRowAndOneDifference(Position fromPosition, Position toPosition) {
        return fromPosition.isSameRank(toPosition)
            && Math.abs(fromPosition.getFileDifference(toPosition)) == SCOPE_DIFFERENCE;
    }

    private boolean isOneDifferenceDiagonal(Position fromPosition, Position toPosition) {
        int height = fromPosition.getRankDifference(toPosition);
        int width = fromPosition.getFileDifference(toPosition);
        return Math.pow(height, 2) + Math.pow(width, 2) == 2;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isCatchable(Position fromPosition, Position toPosition) {
        return isMovable(fromPosition, toPosition);
    }
}
