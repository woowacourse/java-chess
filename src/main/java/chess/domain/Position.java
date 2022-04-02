package chess.domain;

import static chess.domain.PositionRange.COLUMN_RANGE;
import static chess.domain.PositionRange.ROW_RANGE;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> POSITIONS = new HashMap<>();

    private final char column;
    private final char row;

    private Position(final char column, final char row) {
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

    public static Position of(final char column, final char row) {
        return POSITIONS.computeIfAbsent(Character.toString(column) + row
                , k -> new Position(column, row));
    }

    private static Position of(final int column, final int row) {
        return Position.of((char) column, (char) row);
    }

    public static Position from(final String positionString) {
        validatePositionStringLengthEnough(positionString);
        char column = positionString.charAt(0);
        char row = positionString.charAt(1);
        return Position.of(column, row);
    }

    private static void validatePositionStringLengthEnough(final String positionString) {
        if (positionString.length() != 2) {
            throw new IllegalArgumentException("Position 문자열은 길이가 2여야 합니다.");
        }
    }

    public Position move(final int columnAmount, final int rowAmount) {
        return Position.of(column + columnAmount, row + rowAmount);
    }

    public boolean isNotMovableWith(final int columnAmount, final int rowAmount) {
        return COLUMN_RANGE.isOutOfRange(column + columnAmount)
                || ROW_RANGE.isOutOfRange(row + rowAmount);
    }

    public boolean isEndOfRowRange() {
        return ROW_RANGE.isEndOfRange(row);
    }

    public boolean equalsColumn(final Position position) {
        return column == position.column;
    }

    public char getColumn() {
        return column;
    }

    public char getRow() {
        return row;
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

    @Override
    public String toString() {
        return "Position{" + column + row + '}';
    }
}
