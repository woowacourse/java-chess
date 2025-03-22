package chess.board;

public enum Direction {
    UP(-1, 0),
    UP_RIGHT(-1, 1),
    RIGHT(0, 1),
    DOWN_RIGHT(1, 1),
    DOWN(1, 0),
    DOWN_LEFT(1, -1),
    LEFT(0, -1),
    UP_LEFT(-1, -1),
    ;

    private final int deltaRow;
    private final int deltaColumn;

    Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    public int deltaRow() {
        return deltaRow;
    }

    public int deltaColumn() {
        return deltaColumn;
    }
}
