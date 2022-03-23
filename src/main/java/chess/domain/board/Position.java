package chess.domain.board;

import java.util.Objects;

public class Position {
    private final Column column;
    private final Row row;

    public Position(Column column, Row row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position that = (Position) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    public Position flipHorizontally() {
        return new Position(this.column.flip(), this.row);
    }

    public Position flipVertically() {
        return new Position(this.column, this.row.flip());
    }

    public Position flipDiagonally() {
        return new Position(this.column.flip(), this.row.flip());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
