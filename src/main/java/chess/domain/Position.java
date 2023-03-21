package chess.domain;

import chess.domain.math.UnitVector;
import java.util.Objects;

public final class Position {

    private final int row;
    private final int column;

    public Position(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public Position move(final UnitVector unitVector) {
        int nextRow = this.row + unitVector.getRow();
        int nextColumn = this.column + unitVector.getColumn();

        return new Position(nextRow, nextColumn);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
