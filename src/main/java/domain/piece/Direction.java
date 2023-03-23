package domain.piece;

import java.util.Objects;

public class Direction {

    public static final Direction TOP = Direction.of(0, 1);
    public static final Direction TOP_RIGHT = Direction.of(1, 1);
    public static final Direction RIGHT = Direction.of(1, 0);
    public static final Direction BOTTOM_RIGHT = Direction.of(1, -1);
    public static final Direction BOTTOM = Direction.of(0, -1);
    public static final Direction BOTTOM_LEFT = Direction.of(-1, -1);
    public static final Direction LEFT = Direction.of(-1, 0);
    public static final Direction TOP_LEFT = Direction.of(-1, 1);

    public static final Direction TOP_TOP_RIGHT = Direction.of(1, 2);
    public static final Direction TOP_TOP_LEFT = Direction.of(-1, 2);
    public static final Direction RIGHT_RIGHT_TOP = Direction.of(2, 1);
    public static final Direction RIGHT_RIGHT_BOTTOM = Direction.of(2, -1);
    public static final Direction BOTTOM_BOTTOM_RIGHT = Direction.of(1, -2);
    public static final Direction BOTTOM_BOTTOM_LEFT = Direction.of(-1, -2);
    public static final Direction LEFT_LEFT_BOTTOM = Direction.of(-2, -1);
    public static final Direction LEFT_LEFT_TOP = Direction.of(-2, 1);

    public static final Direction TOP_TOP = Direction.of(0, 2);
    public static final Direction BOTTOM_BOTTOM = Direction.of(0, -2);

    private final int x;
    private final int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction of(int column, int row) {
        return new Direction(column, row);
    }

    public boolean isSameDirection(Direction other) {
        Direction thisVector = this.multiply(other.getMaxLength());
        Direction otherVector = other.multiply(getMaxLength());
        return thisVector.equals(otherVector);
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
        Direction direction = (Direction) o;
        return x == direction.x && y == direction.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Direction multiply(int length) {
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

