package domain.piece;

import domain.piece.move.Coordinate;

public abstract class Piece {

    private final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public static Piece ofEmpty() {
        return new EmptyPiece();
    }

    public abstract boolean isMovable(
            final Coordinate start,
            final Coordinate end
    );

    public abstract boolean isAttackable(
            final Coordinate start,
            final Coordinate end,
            final Piece target
    );

    public boolean canJump() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public Color getColor() {
        return color;
    }

    public double getPoint() {
        return 0;
    }
}
