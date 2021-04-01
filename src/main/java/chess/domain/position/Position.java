package chess.domain.position;

import chess.domain.grid.Column;
import chess.domain.grid.Row;

import java.util.Objects;

public final class Position {
    private final Column column;
    private final Row row;

    public Position(final String position) {
        this(Column.column(position.charAt(0)), Row.row(position.charAt(1)));
    }

    public Position(final char x, final char y) {
        this(Column.column(x), Row.row(y));
    }

    public Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public final Column column() {
        return this.column;
    }

    public final Row row() {
        return this.row;
    }

    public final int rowDifference(final Position other) {
        return this.row().difference(other.row());
    }

    public final int columnDifference(final Position other) {
        return this.column().difference(other.column());
    }

    public final Position next(final int xDegree, final int yDegree) {
        return new Position(column.changeColumn(xDegree), row.changeRow(yDegree));
    }

    public final boolean isValidNext(final int xDegree, final int yDegree) {
        char nextColumn = (char) (column.getReference() + xDegree);
        char nextRow = (char) (row.getReference() + yDegree);
        return Column.isValid(nextColumn) && Row.isValid(nextRow);
    }

    public String positionToString() {
        return String.valueOf(this.column.getReference()) + String.valueOf(this.row.getReference());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
