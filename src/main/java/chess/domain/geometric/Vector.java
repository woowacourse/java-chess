package chess.domain.geometric;

import chess.util.DoubleCompare;

import java.util.Objects;

public class Vector {
    private final int x;
    private final int y;

    private Vector(final Position start, final Position end) {
        this.x = end.calculateXDistance(start);
        this.y = end.calculateYDistance(start);
    }

    private Vector(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector of(Position start, Position end) {
        return new Vector(start, end);
    }

    public static Vector of(int x, int y) {
        return new Vector(x, y);
    }

    public boolean isParallelTo(Vector another) {
        return DoubleCompare.deltaCompare(innerProduct(another),
                this.calculateDistance() * another.calculateDistance()
        );
    }

    public double calculateDistance() {
        return Math.sqrt(square(x) + square(y));
    }

    private int square(int number) {
        return number * number;
    }

    private int innerProduct(Vector another) {
        return this.x * another.x + this.y * another.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Vector vector = (Vector) o;
        return x == vector.x &&
                y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean validateDistance(double another) {
        return DoubleCompare.deltaCompare(calculateDistance(), another);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
