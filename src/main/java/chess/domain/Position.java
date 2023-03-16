package chess.domain;

import java.util.Objects;

public class Position implements Comparable<Position> {
    public static final Position NOT_ABLE = new Position(-1, -1);
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
