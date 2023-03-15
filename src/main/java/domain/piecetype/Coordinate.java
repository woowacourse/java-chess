package domain.piecetype;

import java.util.Objects;

public class Coordinate {

    private final double row;
    private final double col;

    public Coordinate(final double row, final double col) {
        this.row = row;
        this.col = col;
    }

    public Coordinate add(double row, double col) {
        return new Coordinate(this.row + row, this.col + col);
    }

    public Coordinate minusWithAbsoluteValue(Coordinate otherCoordinate) {
        return new Coordinate(Math.abs(otherCoordinate.row - this.row), Math.abs(otherCoordinate.col - this.col));
    }

    @Deprecated
    public boolean isSameRow(Coordinate otherCoordinate) {
        return getInclination(otherCoordinate) == 0;
    }

    @Deprecated
    public boolean isSameCol(Coordinate otherCoordinate) {
        return Double.isInfinite(getInclination(otherCoordinate));
    }

    public double getInclination(Coordinate otherCoordinate) {
        return (this.row - otherCoordinate.row) / (this.col - otherCoordinate.col);
    }

    public boolean hasDistanceOfOne(Coordinate otherCoordinate) {
        double x = otherCoordinate.col - this.col;
        double y = otherCoordinate.row - this.row;
        return x <= 1.0 && y <= 1.0;
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
