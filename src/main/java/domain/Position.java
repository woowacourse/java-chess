package domain;

import java.util.Objects;

public class Position {

    private final int row;
    private final int col;

    public Position(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    public Position add(int row, int col) {
        return new Position(this.row + row, this.col + col);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
