package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {

    private static final double KING_SCORE = 0;
    private static final int RANGE = 1;

    public King(Color color) {
        super(color, KING_SCORE);
    }

    @Override
    public boolean isMovable(Position fromPosition, Position toPosition) {
        return isMovableDirection(fromPosition, toPosition) && isMovableRange(fromPosition, toPosition);
    }

    private boolean isMovableDirection(Position fromPosition, Position toPosition) {
        return fromPosition.isCross(toPosition) || fromPosition.isDiagonal(toPosition);
    }

    private boolean isMovableRange(Position fromPosition, Position toPosition) {
        return Math.abs(fromPosition.getFileDifference(toPosition)) == RANGE
            || Math.abs(fromPosition.getRankDifference(toPosition)) == RANGE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
