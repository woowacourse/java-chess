package chess.domain.board;

import java.util.Objects;

class Rank {

    private static final int MIN_VALUE_RANGE = 1;
    private static final int MAX_VALUE_RANGE = 8;

    private final int value;

    public Rank(int value) {
        if (value < MIN_VALUE_RANGE || value > MAX_VALUE_RANGE) {
            throw new IllegalArgumentException("유효한 범위의 숫자가 아닙니다.");
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return value == rank.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Rank{" +
                "value=" + value +
                '}';
    }
}
