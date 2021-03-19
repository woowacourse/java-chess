package chess.domain.position;

import java.util.Objects;

public class Position {
    public static final int RANGE_MIN_PIVOT = 0;
    public static final int RANGE_MAX_PIVOT = 7;
    private final int row;
    private final int col;

    public Position(final int row, final int col) {
        validateRange(row, col);
        this.row = row;
        this.col = col;
    }

    private void validateRange(final int row, final int col) {
        if (row < RANGE_MIN_PIVOT || row > RANGE_MAX_PIVOT || col < RANGE_MIN_PIVOT || col > RANGE_MAX_PIVOT) {
            throw new IllegalArgumentException("잘못된 범위입니다.");
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
