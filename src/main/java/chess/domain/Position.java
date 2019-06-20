package chess.domain;

public class Position {
    private static final int MIN_LIMIT = 1;
    private static final int MAX_LIMIT = 8;

    private final int col;
    private final int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
        validatePosition();
    }

    private void validatePosition() {
        if (isOutOfRange(col) || isOutOfRange(row)) {
            throw new IllegalArgumentException("체스판을 넘었습니다.");
        }
    }

    private boolean isOutOfRange(int number) {
        return number < MIN_LIMIT || number > MAX_LIMIT;
    }
}
