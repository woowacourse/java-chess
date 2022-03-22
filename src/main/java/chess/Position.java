package chess;

import java.util.Objects;

public class Position {

    private int row;
    private int column;

    public Position(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        final Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
