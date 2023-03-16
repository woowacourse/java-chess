package domain.piece;

import java.util.Objects;

public class Vectorr {

    private final int x;
    private final int y;

    private Vectorr(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vectorr of(int column, int row) {
        return new Vectorr(column, row);
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
        Vectorr vectorr = (Vectorr) o;
        return x == vectorr.x && y == vectorr.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vectorr multiply(int length) {
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

