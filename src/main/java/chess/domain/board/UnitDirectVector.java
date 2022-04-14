package chess.domain.board;

public enum UnitDirectVector {
    TOP(0, 1),
    BOTTOM(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),

    TOP_LEFT(-1, 1),
    TOP_RIGHT(1, 1),
    BOTTOM_LEFT(-1, -1),
    BOTTOM_RIGHT(1, -1),

    TOP_TOP_LEFT(-1, 2),
    TOP_TOP_RIGHT(1, 2),
    BOTTOM_BOTTOM_LEFT(-1, -2),
    BOTTOM_BOTTOM_RIGHT(1, -2),

    LEFT_LEFT_TOP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),
    RIGHT_RIGHT_TOP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    ;

    private final int x;
    private final int y;

    UnitDirectVector(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int nextColumnNumber(final int number) {
        return x + number;
    }

    public int nextRowNumber(final int number) {
        return y + number;
    }
}
