package domain.piece;

import domain.piece.move.Coordinate;

public interface Piece {

    private boolean isNeverMoved = true;

    public abstract boolean isReachableByRule(final Coordinate start, final Coordinate end);

    public boolean canJump() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    protected boolean isFirstMove() {
        return isNeverMoved;
    }

    public void checkMoved() {
        isNeverMoved = false;
    }
}
