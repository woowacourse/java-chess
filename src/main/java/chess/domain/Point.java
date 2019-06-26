package chess.domain;

import chess.domain.pieces.Direction;

public class Point {

    private static final int MIN_POINT = 1;
    private static final int MAX_POINT = 8;
    private static final int DIAGONAL = 1;
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isValid(int x, int y) {
        return (x >= MIN_POINT && x <= MAX_POINT)
                && (y >= MIN_POINT && y <= MAX_POINT);
    }

    public boolean isEqualsX(Point another) {
        return this.x == another.x;
    }

    public boolean isEqualsY(Point another) {
        return this.y == another.y;
    }

    public boolean isValidIncline(Point another) {
        return Math.abs(minusY(another) / minusX(another)) == DIAGONAL;
    }

    public int plusX(Point another) {
        return this.x + another.x;
    }

    public int plusY(Point another) {
        return this.y + another.y;
    }

    public int minusX(Point another) {
        return this.x - another.x;
    }

    public int minusY(Point another) {
        return this.y - another.y;
    }

    public Point plusPoint(Direction direction) {
        if (isValid(this.x + direction.getXDegree(), this.y + direction.getYDegree())) {
            return PointFactory.of(this.x + direction.getXDegree(), this.y + direction.getYDegree());
        }
        return PointFactory.getInvalidPoint();
    }

    @Override
    public String toString() {
        return String.format("%c%d", x + 'a' - 1, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}