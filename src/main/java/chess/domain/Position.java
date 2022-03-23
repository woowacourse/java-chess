package chess.domain;

import java.util.Objects;

public class Position {

    private static final char MIN_COLUMN = 'a';
    private static final char MAX_COLUMN = 'h';
    private static final char MIN_ROW = '1';
    private static final char MAX_ROW = '8';

    private final char column;
    private final char row;

    public Position(char column, char row) {
        validateColumnInRange(column);
        validateRowInRange(row);
        this.column = column;
        this.row = row;
    }

    private void validateColumnInRange(char column) {
        if (!isColumnInRange(column)) {
            throw new IllegalArgumentException(
                    String.format("열 위치는 %s~%s 범위에 포함되어야 합니다.", MIN_COLUMN, MAX_COLUMN));
        }
    }

    private boolean isColumnInRange(char column) {
        return MIN_COLUMN <= column && column <= MAX_COLUMN;
    }

    private void validateRowInRange(char row) {
        if (!isRowInRange(row)) {
            throw new IllegalArgumentException(
                    String.format("행 위치는 %s~%s 범위에 포함되어야 합니다.", MIN_ROW, MAX_ROW));
        }
    }

    private boolean isRowInRange(char row) {
        return MIN_ROW <= row && row <= MAX_ROW;
    }

    public boolean equalsColumnOrRow(Position position) {
        return this.column == position.column || this.row == position.row;
    }

    public int calculateDistance(Position position) {
        return Math.abs(this.column - position.column) + Math.abs(this.row - position.row);
    }

    public Position move(int columnAmount, int rowAmount) {
        return new Position((char) (column + columnAmount), (char) (row + rowAmount));
    }

    public boolean isMovable(int columnAmount, int rowAmount) {
        return isColumnInRange((char) (column + columnAmount)) && isRowInRange((char) (row + rowAmount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
