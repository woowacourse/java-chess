package chess.domain.board;

import chess.domain.piece.Direction;
import java.util.Objects;

public class Position {


    private final Column column;
    private final Row row;

    public Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public int calculateRowDifference(final Position target) {
        return row.calculateDifference(target.row);
    }

    public int calculateColumnDifference(final Position target) {
        return column.calculateDifference(target.column);
    }

    public Position move(final Direction direction) {
        return new Position(column.move(direction.getColumn()), row.move(direction.getRow()));
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Position)) {
            return false;
        }
        final Position position = (Position) o;
        return Objects.equals(row, position.row) && Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
