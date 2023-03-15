package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Side;

public abstract class MovablePiece extends Piece {
    private final Side side;

    public MovablePiece(final Side side) {
        this.side = side;
    }

    public boolean isSameSide(final MovablePiece targetPiece) {
        return side.equals(targetPiece.side);
    }

    protected boolean isStraight(final int fileDifference, final int rankDifference) {
        return fileDifference == 0 || rankDifference == 0;
    }

    protected boolean isDiagonal(final int fileDifference, final int rankDifference) {
        return Math.abs(rankDifference) == Math.abs(fileDifference);
    }

    public Color getColor() {
        return side.getColor();
    }

    public abstract boolean isMovable(final int fileDifference, final int rankDifference);
}
