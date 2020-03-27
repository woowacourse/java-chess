package utils;

/**
 * Point on 2-dimensional vector space
 */
public class Point {

    public static Point ORIGIN = new Point(0, 0);

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isDirectionSame(Point point) {
        return ((point.x / this.x) == (point.y / this.y))
            && ((point.x / this.x) > 0);
    }

    public Point plus(Point point) {
        return new Point(
            this.x + point.x,
            this.y + point.y
        );
    }

    public Point minus(Point point) {
        return new Point(
            this.x + point.x,
            this.y + point.y
        );
    }

    public Point multiply(double constant) {
        return new Point(
            this.x * constant,
            this.y * constant
        );
    }

    public Point divide(double constant) {
        return new Point(
            this.x / constant,
            this.y / constant
        );
    }
}
