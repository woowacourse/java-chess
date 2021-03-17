package chess.domain;

import java.util.Objects;

public class Position {

    private Column column;
    private Row row;

    private Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    private Position(String column, String row) {
        this(Column.getColumn(column), Row.getRow(row));
    }

    public static Position of(String value) {
        return new Position(value.substring(0, 1), value.substring(1, 2));
    }

    public static Position of(Column column, Row row) {
        return new Position(column, row);
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
