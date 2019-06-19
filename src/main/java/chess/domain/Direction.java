package chess.domain;

import java.util.Objects;

public class Direction {
    private final int xVector;
    private final int yVector;

    public Direction(int xVector, int yVector) {
        validate(xVector, yVector);
        this.xVector = xVector;
        this.yVector = yVector;
    }

    private void validate(int xVector, int yVector) {
        if (xVector == 0 && yVector == 0) {
            throw new IllegalArgumentException();
        }
        validate(xVector);
        validate(yVector);
    }

    private void validate(int vector) {
        if (vector > 7 || vector < -7) {
            throw new IllegalArgumentException();
        }
    }


    public int xPoint(int xPoint) {
        return xPoint + xVector;
    }

    public int yPoint(int yPoint) {
        return yPoint + yVector;
    }

    public Direction vector() {
        if (xVector * yVector == 0 || Math.abs(xVector) == Math.abs(yVector)) {
            int x = convert(xVector);
            int y = convert(yVector);
            return new Direction(x, y);
        }
        throw new IllegalArgumentException();
    }

    private int convert(int vector) {
        if (vector == 0) {
            return 0;
        }
        return vector / Math.abs(vector);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return xVector == direction.xVector &&
                yVector == direction.yVector;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xVector, yVector);
    }
}
