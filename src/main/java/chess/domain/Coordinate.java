package chess.domain;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction calculateDirection(Coordinate target) {
        return Direction.of(calculatePoint(target.getX()), calculatePoint(target.getY()));
    }

    private int calculatePoint(int x) {
        return x - this.x / Math.abs(x - this.x);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
