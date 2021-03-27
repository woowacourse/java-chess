package chess.domain.piece;

public enum Direction {
    UP(1, 0),
    DOWN(-1, 0),
    LEFT(0, -1),
    LEFT_UP(1, -1),
    LEFT_DOWN(-1, -1),
    RIGHT(0, 1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(-1, 1),
    LEFT_TWO_UP(2, -1),
    LEFT_TWO_DOWN(-2, -1),
    RIGHT_TWO_UP(2, 1),
    RIGHT_TWO_DOWN(-2, 1),
    TWO_LEFT_UP(1, -2),
    TWO_LEFT_DOWN(-1, -2),
    TWO_RIGHT_UP(1, 2),
    TWO_RIGHT_DOWN(-1, 2);

    private final int row;
    private final int column;

    Direction(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
