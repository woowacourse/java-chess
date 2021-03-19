package chess.domain.board;

import java.util.Objects;

public class Position {

    private final Row row;
    private final Column column;

    public Position(Row row, Column col) {
        this.row = row;
        this.column = col;
    }

    public Position(String input) {
        this(Row.findRow(input.charAt(1)), Column.findColumn(input.charAt(0)));
    }

    public Position(int row, int column) {
        this(Row.findRowByIndex(row), Column.findColumnByIndex(column));
    }

    public Column getColumn() {
        return column;
    }

    public int getRowAsIndex() {
        return row.getIndex();
    }

    public int getColumnAsIndex() {
        return column.getIndex();
    }

    public Position nextPosition(int xDegree, int yDegree) {
        return new Position(this.row.getIndex() + yDegree, this.column.getIndex() + xDegree);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
