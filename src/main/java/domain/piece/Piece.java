package domain.piece;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import domain.piece.kind.PieceStatus;

import java.util.Objects;

public abstract class Piece implements Movable {

    protected Point point;
    protected final Color color;

    protected Piece(final Point point, final Color color) {
        this.point = point;
        this.color = color;
    }

    public abstract PieceStatus getStatus();

    public void move(final Point point) {
        this.point = point;
    }

    public boolean isEqualPoint(final Point point) {
        return this.point.equals(point);
    }

    public boolean sameColor(final Piece piece) {
        return this.color == piece.color;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isDirectionStraight(final Point point) {
        return this.point.calculate(point)
                         .isVerticalStraight();
    }

    public boolean isDirectionDiagonal(final Point point) {
        return this.point.calculate(point)
                         .isDiagonal();
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
