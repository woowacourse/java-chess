package piece;

import piece.point.Point;

public abstract class Piece {
    private final Point point;
    private final Color color;

    public Piece(final Point point, final Color color) {
        this.point = point;
        this.color = color;
    }
}
