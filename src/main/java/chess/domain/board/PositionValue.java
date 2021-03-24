package chess.domain.board;

import java.util.Objects;

public class PositionValue {
    private final int value;

    public PositionValue(int value) {
        this.value = value;
    }

    public int subtract(PositionValue anotherPositionValue) {
        return value - anotherPositionValue.value;
    }

    public int add(PositionValue anotherPositionValue) {
        return this.value + anotherPositionValue.value;
    }

    public boolean isSameAs(int position) {
        return this.value == position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PositionValue positionValue1 = (PositionValue)o;
        return value == positionValue1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
