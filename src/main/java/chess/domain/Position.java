package chess.domain;

public class Position {
    private final int row;
    private final int column;

    public Position(final int row, final int column) {
        validate(row, column);
        this.row = row;
        this.column = column;
    }

    private void validate(final int row, final int column) {
        if (row < 0) {
            throw new IllegalArgumentException("가로 위치는 0 미만일 수 없습니다.");
        }
        if (column < 0) {
            throw new IllegalArgumentException("세로 위치는 0 미만일 수 없습니다.");
        }
    }
}
