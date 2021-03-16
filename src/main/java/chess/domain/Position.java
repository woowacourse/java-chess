package chess.domain;

import java.util.Objects;

public class Position {

    private Row row;
    private Column column;

    public Position(int row, int column){
        this(Row.getRow(row), Column.getColumn(column));
    }

    public Position(Row row, Column column){
        this.row = row;
        this.column = column;
    }

    public static Position of(int row, int column) {
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
