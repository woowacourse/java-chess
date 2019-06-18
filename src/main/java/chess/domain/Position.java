package chess.domain;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private static final int DIAGONAL_INCLINATION = 1;
    private static final int RIGHT_INCLINATION = 0;
    private static final int ZERO = 0;

    private final Row row;
    private final Column column;

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final Row row, final Column column) {
        return new Position(row, column);
    }

    public static Position of(final String row, final String column) {
        return Position.of(Row.from(row), Column.from(column));
    }

    public boolean isDiagonal(final Position target) {
        int height = this.row.calculateAbsolute(target.row);
        int width = this.column.calculateAbsolute(target.column);
        if (width == ZERO) {
            return false;
        }
        return Math.abs(height / width) == DIAGONAL_INCLINATION;
    }

    public boolean isPerpendicular(final Position target) {
        return this.column.calculateAbsolute(target.column) == RIGHT_INCLINATION;
    }

    public boolean isLevel(final Position target) {
        return this.row.calculateAbsolute(target.row) == RIGHT_INCLINATION;
    }

    public boolean isMoveAnyWhereSum(final Position position, int distance) {
        return sumRowAndColumn(position) == distance;
    }

    public boolean isMoveAnyWhereSub(final Position position, int distance) {
        return subRowAndColumn(position) == distance;
    }

    public int subRowAndColumn(final Position target) {
        return Math.abs(this.distanceOfRow(target) - this.distanceOfColumn(target));
    }

    public int sumRowAndColumn(final Position target) {
        return Math.abs(this.distanceOfRow(target) + this.distanceOfColumn(target));
    }

    private int distanceOfRow(final Position target) {
        return this.row.calculateAbsolute(target.row);
    }

    private int distanceOfColumn(final Position target) {
        return this.column.calculateAbsolute(target.column);
    }

    @Override
    public int compareTo(final Position o) {
        int result = this.row.compareTo(o.row);

        if (result == 0) {
            result = this.column.compareTo(o.column);
        }

        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return Objects.equals(row, position.row) &&
                Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
