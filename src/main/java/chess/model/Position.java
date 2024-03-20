package chess.model;

import java.util.Objects;

public class Position {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 7;
    private final int row;
    private final int column;

    public Position(int row, int column) {
        validateRange(row, column);
        this.row = row;
        this.column = column;
    }

    private void validateRange(int row, int column) {
        if (row < MIN_VALUE || row > MAX_VALUE || column < MIN_VALUE || column > MAX_VALUE) {
            throw new IllegalArgumentException("좌표 값은 1이상 8이하의 값만 가능합니다.");
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
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
