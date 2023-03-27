package chess.domain.position;

import java.util.Objects;

public class Row {

    private static final int MAX_ROW = 7;
    private static final int MIN_ROW = 0;
    static final String INVALID_RANGE_OF_VALUE = "행은 최소 0, 최대 7을 가질 수 있습니다.";

    private final int row;

    public Row(final int row) {
        validateRow(row);
        this.row = row;
    }

    private void validateRow(final int row) {
        if (row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException(INVALID_RANGE_OF_VALUE);
        }
    }

    public Row add(final int otherRow) {
        return new Row(this.row + otherRow);
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row1 = (Row) o;
        return row == row1.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
}
