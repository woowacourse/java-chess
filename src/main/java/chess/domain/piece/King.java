package chess.domain.piece;

import chess.domain.Side;

public class King extends MovablePiece {
    private static final int MAX_MOVE_DISTANCE = 1;

    public King(final Side side) {
        super(side);
    }

    @Override
    public boolean isMovable(final int fileDifference, final int rankDifference) {
        int moveDistance = Math.max(Math.abs(fileDifference), Math.abs(rankDifference));
        return moveDistance == MAX_MOVE_DISTANCE &&
                (isDiagonal(fileDifference, rankDifference) || isStraight(fileDifference, rankDifference));
    }
}
