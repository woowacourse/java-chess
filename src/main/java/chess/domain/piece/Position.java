package chess.domain.piece;

import java.util.Objects;

public final class Position {
    private final int column;
    private final int row;

    public Position(final int column, final int row) {
        this.column = column;
        this.row = row;
    }

    public Position calculate(final int columnMove, final int rowMove) {
        return new Position(this.column + columnMove, this.row + rowMove);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "Position{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
