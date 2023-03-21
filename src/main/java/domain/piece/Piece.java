package domain.piece;

import domain.piece.move.Coordinate;

public abstract class Piece {

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
}
