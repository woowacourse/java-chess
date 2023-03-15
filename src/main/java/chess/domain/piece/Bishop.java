package chess.domain.piece;

import chess.domain.Side;

public class Bishop extends MovablePiece {

    public Bishop(final Side side) {
        super(side);
    }

    @Override
    public boolean isMovable(final int fileDifference, final int rankDifference) {
        return isDiagonal(fileDifference, rankDifference);
    }
}
