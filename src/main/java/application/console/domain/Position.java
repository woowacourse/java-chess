package application.console.domain;

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

    public static Position from(final String positionString) {
        validatePositionStringLengthEnough(positionString);
        char column = positionString.charAt(0);
        char row = positionString.charAt(1);
        return new Position(column, row);
    }

    private static void validatePositionStringLengthEnough(final String positionString) {
        if (positionString.length() != 2) {
            throw new IllegalArgumentException("Position 문자열은 길이가 2여야 합니다.");
        }
    }

    private void validateColumnInRange(final char column) {
        if (PositionRange.COLUMN_RANGE.isOutOfRange(column)) {
            throw new IllegalArgumentException(String.format("열 위치는 %s~%s 범위에 포함되어야 합니다.",
                    PositionRange.COLUMN_RANGE.getAllowedMinimum(), PositionRange.COLUMN_RANGE.getAllowedMaximum()));
        }
    }

    private void validateRowInRange(final char row) {
        if (PositionRange.ROW_RANGE.isOutOfRange(row)) {
            throw new IllegalArgumentException(String.format("행 위치는 %s~%s 범위에 포함되어야 합니다.",
                    PositionRange.ROW_RANGE.getAllowedMinimum(), PositionRange.ROW_RANGE.getAllowedMaximum()));
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
