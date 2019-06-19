package chess.domain;

public class Point {
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

    public boolean isEqualsX(Point another) {
        return this.x == another.x;
    }

    public boolean isEqualsY(Point another) {
        return this.y == another.y;
    }

    public double calculateIncline(Point another) {
        return minusY(another) / minusX(another);
    }

    public int minusX(Point another) {
        return this.x - another.x;
    }

    public int minusY(Point another) {
        return this.y - another.y;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
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
