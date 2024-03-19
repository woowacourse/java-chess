package chess.domain;

public record Position(int row, int column) {
    private static final int MIN_ROW = 1;
    private static final int MAX_ROW = 8;
    private static final int MIN_COLUMN = 1;
    private static final int MAX_COLUMN = 8;


    public Position {
        validateRange(row, column);
    }

    private void validateRange(int row, int column) {
        if (row < MIN_ROW || row > MAX_ROW) {
            throw new IllegalArgumentException(
                    "가로는 %d부터 %d 사이의 값이어야 합니다: %d".formatted(MIN_ROW, MAX_ROW, row));
        }
        if (column < MIN_COLUMN || column > MAX_COLUMN) {
            throw new IllegalArgumentException(
                    "세로는 %d부터 %d 사이의 값이어야 합니다: %d".formatted(MIN_COLUMN, MAX_COLUMN, column));
        }
    }
}
