package chess.domain.piece;

import chess.domain.Side;

public class Queen extends MovablePiece {
    public Queen(final Side side) {
        super(side);
    }

    @Override
    public boolean isMovable(final int rankGap, final int fileGap) {
        return isDiagonal(rankGap, fileGap) || isStraight(rankGap, fileGap);
    }
}
