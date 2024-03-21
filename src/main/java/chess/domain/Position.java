package chess.domain;

public record Position(int file, int rank) {
    private static final int MIN_POSITION_RANGE = 1;
    private static final int MAX_POSITION_RANGE = 8;

    public Position {
        validateRange(file);
        validateRange(rank);
    }

    private void validateRange(int position) {
        if (position < MIN_POSITION_RANGE || position > MAX_POSITION_RANGE) {
            throw new IllegalArgumentException("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
        }
    }

    public boolean isMinimumColumn() {
        return file == MIN_POSITION_RANGE;
    }

    public boolean isMaximumColumn() {
        return file == MAX_POSITION_RANGE;
    }

    public boolean isMinimumRow() {
        return rank == MIN_POSITION_RANGE;
    }

    public boolean isMaximumRow() {
        return rank == MAX_POSITION_RANGE;
    }
}
