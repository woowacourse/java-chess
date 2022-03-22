package chess.domain;

import java.util.Objects;

public class Position {

    private static final char MIN_ROW = 'a';
    private static final char MAX_ROW = 'h';
    private static final char MIN_COLUMN = '1';
    private static final char MAX_COLUMN = '8';

    private final char row;
    private final char column;

    public Position(char row, char column) {
        validateRowInRange(row);
        validateColumnInRange(column);
        this.row = row;
        this.column = column;
    }

    private void validateRowInRange(char row) {
        if (row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException(
                    String.format("가로 위치는 %s~%s 범위에 포함되어야 합니다.", MIN_ROW, MAX_ROW));
        }
    }

    private void validateColumnInRange(char column) {
        if (column < MIN_COLUMN || column > MAX_COLUMN) {
            throw new IllegalArgumentException(
                    String.format("세로 위치는 %s~%s 범위에 포함되어야 합니다.", MIN_COLUMN, MAX_COLUMN));
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
