package chess;

import chess.piece.Direction;
import java.util.Objects;

public final class Position {

    private final Column column;

    private final Row row;

    public Position(final Column column, final Row row) {
        this(row, column);
    }

    public Position(final Row row, final Column column) {
        this.row = row;
        this.column = column;
    }

    public boolean canMove(final Direction direction) {
        return column.canMove(direction.x()) && row.canMove(direction.y());
    }

    public Position move(final Direction direction) {
        return new Position(column.move(direction.x()), row.move(direction.y()));
    }

    public Column column() {
        return column;
    }

    public Row row() {
        return row;
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
