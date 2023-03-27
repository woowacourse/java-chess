package chessgame.domain.coordinate;

import java.util.Objects;

public class Coordinate {

    private final Row row;
    private final Column column;

    private Coordinate(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Coordinate createOnBoard(final int row, final int column) {
        return new Coordinate(Row.from(row), Column.from(column));
    }

    public static Coordinate createWithoutValidate(final int row, final int column) {
        return new Coordinate(Row.createWithoutValidate(row), Column.createWithoutValidate(column));
    }

    public Coordinate add(final int row, final int column) {
        return createOnBoard(this.row.add(row), this.column.add(column));
    }

    public Coordinate minus(final Coordinate otherCoordinate) {
        return createWithoutValidate(otherCoordinate.row.minus(this.row), otherCoordinate.column.minus(this.column));
    }

    public Coordinate minusWithAbsoluteValue(final Coordinate otherCoordinate) {
        return createOnBoard(this.row.absoluteOfMinus(otherCoordinate.row),
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

    public Inclination getInclination(final Coordinate otherCoordinate) {
        int differenceRow = this.row.minus(otherCoordinate.row);
        int differenceColumn = this.column.minus(otherCoordinate.column);
        double inclination = (double) differenceRow / differenceColumn;
        return Inclination.of(inclination);
    }

    public boolean hasDistanceLessThan(final Coordinate otherCoordinate, final double distance) {
        int differenceRow = this.row.minus(otherCoordinate.row);
        int differenceColumn = this.column.minus(otherCoordinate.column);
        return Math.abs(differenceRow) <= distance && Math.abs(differenceColumn) <= distance;
    }

    public boolean isSameColumn(Coordinate otherCoordinate) {
        return this.column.equals(otherCoordinate.column);
    }

    public int row() {
        return row.value();
    }

    public int column() {
        return column.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(row, that.row) && Objects.equals(column, that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
