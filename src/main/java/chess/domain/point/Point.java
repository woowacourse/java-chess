package chess.domain.point;

import java.util.Objects;

public final class Point {
    public static final Point ZERO = new Point(0.0);

    private final double value;

    private Point(final double value) {
        this.value = value;
    }

    public static Point create(final double value) {
        return new Point(value);
    }

    public Point plus(final Point point) {
        return new Point(value + point.value);
    }

    public Point minus(final Point point) {
        return new Point(value - point.value);
    }

    public Point times(final int count) {
        return new Point(value * count);
    }

    public boolean isGreaterThan(final Point point) {
        return value > point.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Point point = (Point) o;
        return Double.compare(point.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
