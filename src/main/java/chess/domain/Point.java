package chess.domain;

import java.util.Objects;

public class Point {
    private final int x;
    private final int y;


    public Point(int x, int y) {
        validate(x);
        validate(y);
        this.x = x;
        this.y = y;
    }

    private void validate(int point) {
        if (point > 8 || point < 0) {
            throw new IllegalArgumentException("범위가 잘 못 되었습니다.");
        }
    }

    public Point transport(Direction direction) {
        return new Point(direction.xPoint(x), direction.yPoint(y));
    }

    public Direction direction(Point p2) {
        return new Direction(p2.x - x, p2.y - y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
