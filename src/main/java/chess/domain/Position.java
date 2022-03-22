package chess.domain;

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
            throw new IllegalArgumentException("가로 위치는 a~h 범위에 포함되어야 합니다.");
        }
    }

    private void validateColumnInRange(char column) {
        if (column < MIN_COLUMN || column > MAX_COLUMN) {
            throw new IllegalArgumentException("세로 위치는 1~8 범위에 포함되어야 합니다.");
        }
    }
}
