package domain.utils;

public enum Direction {

    EAST(0, 1),
    WEST(0, -1),
    SOUTH(-1, 0),
    NORTH(1, 0);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
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
