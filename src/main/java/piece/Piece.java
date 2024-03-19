package piece;

import piece.point.Point;

public abstract class Piece {
    private final Point point;

    protected Piece(final Point point) {
        this.point = point;
    }
}
