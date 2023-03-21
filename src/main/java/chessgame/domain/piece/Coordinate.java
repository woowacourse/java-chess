package chessgame.domain.piece;

import java.util.Objects;

public class Coordinate {

    private final Row row;
    private final Column column;

    public Coordinate(final int row, final int column) {
        this.row = Row.from(row);
        this.column = Column.from(column);
    }

    public Coordinate add(Coordinate otherCoordinate) {
        return new Coordinate(this.row.add(otherCoordinate.row), this.column.add(otherCoordinate.column));
    }

    public Coordinate add(int row, int column) {
        return new Coordinate(this.row.add(row), this.column.add(column));
    }

    public Coordinate minus(Coordinate otherCoordinate) {
        return new Coordinate(this.row.minus(otherCoordinate.row), otherCoordinate.column.minus(this.column));
    }

    public Coordinate minusWithAbsoluteValue(Coordinate otherCoordinate) {
        return new Coordinate(this.row.absoluteOfMinus(otherCoordinate.row),
                this.column.absoluteOfMinus(otherCoordinate.column));
    }

    public boolean hasPositiveRowValue() {
        return row.isPositive();
    }

    public boolean hasNegativeRowValue() {
        return row.isNegative();
    }

    public boolean isRowZero() {
        return row.isZero();
    }

    public boolean hasPositiveColValue() {
        return column.isPositive();
    }

    public boolean hasNegativeColValue() {
        return column.isNegative();
    }

    public boolean isColZero() {
        return column.isZero();
    }

    public Inclination getInclination(Coordinate otherCoordinate) {
        int differenceRow = this.row.minus(otherCoordinate.row);
        int differenceColumn = this.column.minus(otherCoordinate.column);
        double inclination = (double) differenceRow / differenceColumn;
        return Inclination.of(inclination);
    }

    public boolean hasDistanceLessThan(Coordinate otherCoordinate, double distance) {
        int differenceRow = this.row.minus(otherCoordinate.row);
        int differenceColumn = this.column.minus(otherCoordinate.column);
        return Math.abs(differenceRow) <= distance && Math.abs(differenceColumn) <= distance;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate coordinate = (Coordinate) o;
        return row == coordinate.row && column == coordinate.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
