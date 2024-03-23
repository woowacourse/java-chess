package domain.coordinate;

import java.util.Objects;

public class Position {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 7;

    private final int value;

    public Position(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (MAX_VALUE < value || value < MIN_VALUE) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    public Position moveBy(int offSet) {
        return new Position(value + offSet);
    }

    public int calculateDifference(Position other) {
        return other.value - value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position1 = (Position) o;
        return value == position1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
