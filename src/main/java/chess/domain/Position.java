package chess.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Position implements Comparable<Position> {
    public static final Position IS_NULL = new Position(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private final int row;
    private final int column;

    private Position(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final int row, final int column) {
        return new Position(row, column);
    }

    public static Position from(final String rowColumn) {
        final int row = Row.findIndex(String.valueOf(rowColumn.charAt(0)));
        final int column = Column.findIndex(String.valueOf(rowColumn.charAt(1)));

        return new Position(row, column);
    }

    private int rowDirection(Position end) {
        return Integer.compare(end.row, this.row);
    }

    private int columnDirection(Position end) {
        return Integer.compare(end.column, this.column);
    }

    public List<Position> calculateBetweenPoints(Position end) {
        int rowDirection = rowDirection(end);
        int columnDirection = columnDirection(end);
        int maxDistance = Math.max(Math.abs(row - end.row), Math.abs(column - end.column));

        return IntStream.rangeClosed(1, maxDistance)
                .mapToObj(distance -> Position.of(row + distance * rowDirection, column + distance * columnDirection))
                .collect(Collectors.toList());
    }

    public boolean isNotLinearFunction(Position end) {
        final int slopeOfOne = 1;
        if ((end.column - this.column) == 0 || (end.row - this.row) == 0) {
            return true;
        }
        final int slope = Math.abs((end.column - this.column) / (end.row - this.row));
        return slope != slopeOfOne;
    }

    public boolean isSameRow(int row) {
        return this.row == row;
    }

    public boolean isSameColumn(int column) {
        return this.column == column;
    }

    public boolean isNotConstantFunction(Position position) {
        return this.row != position.getRow() && this.column != position.column;
    }

    public boolean isNotDistanceTwo(Position position) {
        int distanceTwo = 2;
        return distanceTwo != Math.abs(this.row - position.row) + Math.abs(this.column - position.column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int compareTo(final Position position) {
        final int columnCompare = Integer.compare(this.column, position.column);

        if (columnCompare == 0) {
            return Integer.compare(this.row, position.row);
        }

        return columnCompare;
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
