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
        if (column < MIN_COLUMN || column > MAX_COLUMN) {
            throw new IllegalArgumentException(
                    String.format("열 위치는 %s~%s 범위에 포함되어야 합니다.", MIN_COLUMN, MAX_COLUMN));
        }
    }

    private void validateRowInRange(char row) {
        if (row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException(
                    String.format("행 위치는 %s~%s 범위에 포함되어야 합니다.", MIN_ROW, MAX_ROW));
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
