package domain.piece;

import java.util.Objects;

public class Vector {

    private final int x;
    private final int y;

    private Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector of(int column, int row) {
        return new Vector(column, row);
    }

    public int getMaxLength() {
        return Math.max(Math.abs(x), Math.abs(y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector multiply(int length) {
        return of(x * length, y * length);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Vectorr{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}

