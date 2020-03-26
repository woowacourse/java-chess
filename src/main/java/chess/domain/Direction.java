package chess.domain;

public enum Direction {
    UP(1, 0),
    RIGHT_UP(1, 1),
    RIGHT(0, 1),
    RIGHT_DOWN(-1, 1),
    DOWN(-1, 0),
    LEFT_DOWN(-1, -1),
    LEFT(0, -1),
    LEFT_UP(1, -1);

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
