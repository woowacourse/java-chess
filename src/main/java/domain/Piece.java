package domain;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

import java.util.Objects;

public abstract class Piece {

    private final Point point;
    private final Color color;

    protected Piece(final Point point, final Color color) {
        this.point = point;
        this.color = color;
    }

    public abstract PieceStatus getStatus();

    public boolean isEqualPoint(final Point point) {
        return this.point.equals(point);
    }

    public boolean isEqualColor(final Color color) {
        return this.color == color;
    }

    public Point getPoint() {
        return this.point;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final Piece piece)) return false;
        return Objects.equals(point, piece.point) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, color);
    }

}
