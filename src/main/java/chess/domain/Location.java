package chess.domain;

public record Location(int row, int column) {
    private static final int MIN_LOCATION = 1;
    private static final int MAX_LOCATION = 8;


    public Location {
        validateRange(row, column);
    }

    private void validateRange(int row, int column) {
        if (isOutOfRange(row) || isOutOfRange(column)) {
            throw new IllegalArgumentException("위치는 1부터 8 사이의 값이어야 합니다.");
        }
    }

    private boolean isOutOfRange(int value) {
        return value < MIN_LOCATION || value > MAX_LOCATION;
    }
}
