package domain;

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

    public boolean isSameRow(Coordinate otherCoordinate) {
        return inclination(otherCoordinate) == 0;
    }

    public boolean isSameCol(Coordinate otherCoordinate) {
        return Double.isInfinite(inclination(otherCoordinate));
    }

    private double inclination(Coordinate otherCoordinate) {
        return Math.abs(this.row - otherCoordinate.row) / Math.abs(this.col - otherCoordinate.col);
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
