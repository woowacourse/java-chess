package chess.console.domain;

import static chess.console.domain.PositionRange.COLUMN_RANGE;
import static chess.console.domain.PositionRange.ROW_RANGE;

import java.util.Objects;

public class Position {

    private final char column;
    private final char row;

    public Position(final char column, final char row) {
        validateColumnInRange(column);
        validateRowInRange(row);
        this.column = column;
        this.row = row;
    }

    private void validateColumnInRange(final char column) {
        if (COLUMN_RANGE.isOutOfRange(column)) {
            throw new IllegalArgumentException(String.format("열 위치는 %s~%s 범위에 포함되어야 합니다.",
                    COLUMN_RANGE.getAllowedMinimum(), COLUMN_RANGE.getAllowedMaximum()));
        }
    }

    private void validateRowInRange(final char row) {
        if (ROW_RANGE.isOutOfRange(row)) {
            throw new IllegalArgumentException(String.format("행 위치는 %s~%s 범위에 포함되어야 합니다.",
                    ROW_RANGE.getAllowedMinimum(), ROW_RANGE.getAllowedMaximum()));
        }
    }

    @Override
    public boolean equals(Object o) {
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
}
