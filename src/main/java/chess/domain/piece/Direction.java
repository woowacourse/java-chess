package chess.domain.piece;

public enum Direction {
    TOP(0, 1),
    RIGHT_TOP(1, 1),
    RIGHT(1, 0),
    RIGHT_BOTTOM(1, -1),
    BOTTOM(0, -1),
    LEFT_BOTTOM(-1, -1),
    LEFT(-1, 0),
    LEFT_TOP(-1, 1);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
