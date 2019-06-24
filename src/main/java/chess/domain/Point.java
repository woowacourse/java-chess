package chess.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Point {
    public static final int MAX_AXIS = 8;
    public static final int MIN_AXIS = 1;
    public static final int ASCHII_CODE_NUMBER = 96;
    private static Set<Point> points;

    static {
        points = new HashSet<>();
        for (int i = MIN_AXIS; i <= MAX_AXIS; i++) {
            for (int j = MIN_AXIS; j <= MAX_AXIS; j++) {
                points.add(new Point(i, j));
            }
        }
    }

    private final int x;
    private final int y;


    private Point(int x, int y) {
        validate(x);
        validate(y);
        this.x = x;
        this.y = y;
    }

    public static Point get(int xAxis, int yAxis) {
        validate(xAxis);
        validate(yAxis);
        return points.stream()
                .filter(point -> point.x == xAxis)
                .filter(point -> point.y == yAxis)
                .findFirst()
                .get();
    }

    private static void validate(int point) {
        if (point > MAX_AXIS || point < MIN_AXIS) {
            throw new IllegalArgumentException("범위가 잘 못 되었습니다.");
        }
    }

    public Point transport(Direction direction) {
        return new Point(direction.xPoint(x), direction.yPoint(y));
    }

    public Direction direction(Point p2) {
        return new Direction(p2.x - x, p2.y - y);
    }

    public int getX() {
        return this.x;
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

    @Override
    public String toString() {
        return (char) (x + ASCHII_CODE_NUMBER) + String.valueOf(y);
    }
}
