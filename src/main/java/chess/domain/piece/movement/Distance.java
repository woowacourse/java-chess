package chess.domain.piece.movement;

import java.util.Objects;

public class Distance {
    public static final Distance ONE = new Distance(1);
    public static final Distance TWO = new Distance(2);
    public static final Distance SEVEN = new Distance(7);

    private int value;

    public Distance(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Distance는 0이상이어야 한다.");
        }
        this.value = value;
    }

    public boolean isBelow(final Distance other) {
        return this.value <= other.value;
    }

    public int getValue() {
        return value;
    }

    public Distance pre() {
        return new Distance(value - 1);
    }

    public Distance next() {
        return new Distance(value + 1);
    }

    public boolean isFirst() {
        return this.equals(ONE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return value == distance.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
