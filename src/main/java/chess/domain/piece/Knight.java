package chess.domain.piece;

import chess.domain.Side;

public class Knight extends MovablePiece {
    private static final int MAX_MOVE_DISTANCE = 2;

    public Knight(final Side side) {
        super(side);
    }

    @Override
    public boolean isMovable(final int fileDifference, final int rankDifference) {
        int moveDistance = Math.max(Math.abs(fileDifference), Math.abs(rankDifference));
        return moveDistance == MAX_MOVE_DISTANCE && Math.abs(fileDifference * rankDifference) == 2;
    }
}
