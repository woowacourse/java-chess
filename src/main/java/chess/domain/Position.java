package chess.domain;

import chess.domain.direction.Direction;

import java.util.Objects;

public class Position implements Comparable<Position> {
    public static final Position NOT_EXISTS = new Position(null, null);

    private final Row row;
    private final Column column;

    private Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(final Row row, final Column column) {
        return new Position(row, column);
    }

    public static Position from(final String command) {
        final Row row = Row.from(String.valueOf(command.charAt(0)));
        final Column column = Column.from(String.valueOf(command.charAt(1)));

        return new Position(row, column);
    }

    public Position move(final Direction direction) {
        final Row movedRow = row.move(direction);
        final Column movedColumn = column.move(direction);

        return Position.of(movedRow, movedColumn);
    }

    public int diff(final Row otherRow) {
        return this.row.diff(otherRow);
    }

    public int diff(final Column otherColumn) {
        return this.column.diff(otherColumn);
    }

    public int findDirection(final Row otherRow) {
        return row.findDirection(otherRow);
    }

    public int findDirection(final Column othreColumn) {
        return column.findDirection(othreColumn);
    }
    public boolean isRangeOk(final Direction direction) {
        return row.isMovable(direction) && column.isMovable(direction);
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public int compareTo(final Position otherPosition) {
        final int columnCompare = otherPosition.column.findDirection(column);
        if (columnCompare == 0) {
            return otherPosition.row.findDirection(row);
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
