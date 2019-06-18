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
