package chess.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Point {
    private static final int MIN_POINT_NUMBER = 1;
    private static final int MAX_POINT_NUMBER = 8;
    private static final String COMMA = ",";
    private static final Map<String, Point> points = new HashMap<>();

    private final int x;
    private final int y;

    static {
        for (int i = 1; i <= 8; i++) {
            setPoints(i);
        }
    }

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(int x, int y) {
        checkValidPoint(x, y);
        return points.get(x + COMMA + y);
    }

    private static void checkValidPoint(int x, int y) {
        if (MIN_POINT_NUMBER > x || MAX_POINT_NUMBER < x || MIN_POINT_NUMBER > y || MAX_POINT_NUMBER < y) {
            throw new IllegalArgumentException("올바르지 않은 위치입니다.");
        }
    }

    public double calculateDistance(Point other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    public Direction calculateDirection(Point other) {
        int xDistance = other.x - this.x;
        int yDistance = other.y - this.y;
        return Direction.valueOf(xDistance, yDistance);
    }

    public Point next(Direction direction) {
        return of(x + direction.getXDegree(), y + direction.getYDegree());
    }

    public int getX() {
        return x;
    }

    private static void setPoints(int i) {
        for (int j = 1; j <= 8; j++) {
            points.put(i + COMMA + j, new Point(i, j));
        }
    }

    @Override
    public String toString() {
        return x + COMMA + y;
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
