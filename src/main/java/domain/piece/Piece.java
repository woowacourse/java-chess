package domain.piece;

import domain.piece.point.Point;

public abstract class Piece {

    private final Point point;
    private final Color color;

    protected Piece(final Point point, final Color color) {
        this.point = point;
        this.color = color;
    }
}
