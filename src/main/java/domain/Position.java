package domain;

import java.util.Objects;

public class Position {
    private final Column column;
    private final Row row;

    public Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }


    public Position moveBy(int columnValue, int rowValue) {
        return new Position(column.moveBy(columnValue), row.moveBy(rowValue));
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
