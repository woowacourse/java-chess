package domain.path.location;

import java.util.Objects;

public final class Row {

    private static final int MAX_RANGE = 7;
    private static final int MIN_RANGE = 0;
    
    private final int value;

    private Row(final int value) {
        this.value = value;
    }

    public static Row valueOf(final int value) {
        if (MIN_RANGE > value || value > MAX_RANGE) {
            throw new IllegalArgumentException("행은 0이상 7이하의 숫자여야 합니다.");
        }
        return new Row(value);
    }

    public Row add(final int addValue) {
        return new Row(this.value + addValue);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Row row = (Row) o;
        return value == row.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
