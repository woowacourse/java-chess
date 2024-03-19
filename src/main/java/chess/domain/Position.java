package chess.domain;

public record Position(int row, int column) {
    private static final int MIN_POSITION_RANGE = 1;
    private static final int MAX_POSITION_RANGE = 8;

    public Position {
        validateRange(row);
        validateRange(column);
    }

    private void validateRange(int position) {
        if (position < MIN_POSITION_RANGE || position > MAX_POSITION_RANGE) {
            throw new IllegalArgumentException("위치의 가로, 세로 범위는 각각 1 ~ 8이여야 합니다.");
        }
    }

    public boolean isMinimumColumn() {
        return column == MIN_POSITION_RANGE;
    }

    public boolean isMaximumColumn() {
        return column == MAX_POSITION_RANGE;
    }

    public boolean isMinimumRow() {
        return row == MIN_POSITION_RANGE;
    }

    public boolean isMaximumRow() {
        return row == MAX_POSITION_RANGE;
    }
}
