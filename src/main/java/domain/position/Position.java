package domain.position;

import java.util.Objects;

public class Position {

    private final Row row;
    private final Column column;

    public Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String row, String column) {
        return new Position(Row.of(row), Column.of(column));
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

    public int getRow() {
        return row.getIndex();
    }

    public int getColumn() {
        return column.getIndex();
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
