package chess.domain;

import java.util.Objects;

public class Position {

    private Row row;
    private Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    private Position(String row, String column) {
        this(Row.getRow(row), Column.getColumn(column));
    }

    public static Position of(String value) {
        return new Position(value.substring(0, 1), value.substring(1, 2));
    }

    public static Position of(Row row, Column column) {
        return new Position(row, column);
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
        return row == position.row &&
                column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
