package chess;

import java.text.MessageFormat;
import java.util.Objects;

public class Position {

    private int row;
    private int column;

    public Position(final int row, final int column) {
        validatePosition(row, column);
        this.row = row;
        this.column = column;
    }

    private void validatePosition(final int row, final int column) {
        if (row < 0 || column < 0 || row > 7 || column > 7) {
            throw new IllegalArgumentException(
                    MessageFormat.format("체스판 범위에서 벗어났습니다. row: {0}, column: {1}", row, column)
            );
        }
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
