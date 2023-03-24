package domain.piece.move;

import java.util.Objects;

public final class Coordinate {

    private final int row;
    private final int col;

    public Coordinate(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    public Coordinate add(final Coordinate otherCoordinate) {
        return new Coordinate(
                this.row + otherCoordinate.row,
                this.col + otherCoordinate.col
        );
    }

    public Coordinate minus(final Coordinate otherCoordinate) {
        return new Coordinate(
                otherCoordinate.row - this.row,
                otherCoordinate.col - this.col
        );
    }

    public Coordinate minusWithAbsoluteValue(final Coordinate otherCoordinate) {
        return new Coordinate(
                Math.abs(otherCoordinate.row - this.row),
                Math.abs(otherCoordinate.col - this.col)
        );
    }

    public double getInclination(final Coordinate otherCoordinate) {
        return ((double)otherCoordinate.row - this.row) /
                (otherCoordinate.col - this.col);
    }

    public boolean hasDistanceLessThanOne(final Coordinate otherCoordinate) {
        double x = otherCoordinate.col - this.col;
        double y = otherCoordinate.row - this.row;
        return Math.abs(x) <= 1.0 && Math.abs(y) <= 1.0;
    }

    public boolean hasDistanceLessThanTwo(final Coordinate otherCoordinate) {
        double x = otherCoordinate.col - this.col;
        double y = otherCoordinate.row - this.row;
        return Math.abs(x) <= 2.0 && Math.abs(y) <= 2.0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate coordinate = (Coordinate) o;
        return row == coordinate.row && col == coordinate.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
