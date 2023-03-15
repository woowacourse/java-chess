package chess.domain.piece;

import chess.domain.Side;

public class Rook extends MovablePiece {
    public Rook(final Side side) {
        super(side);
    }

    @Override
    public boolean isMovable(final int fileDifference, final int rankDifference) {
        return isStraight(fileDifference, rankDifference);
    }
}
