package domain.path.location;

import java.util.Objects;

public final class Column {

    private static final int MAX_RANGE = 7;
    private static final int MIN_RANGE = 0;
    
    private final int value;

    private Column(final int value) {
        this.value = value;
    }

    public static Column valueOf(final int value) {
        if (MIN_RANGE > value || value > MAX_RANGE) {
            throw new IllegalArgumentException("열은 0이상 7이하의 숫자여야 합니다.");
        }
        return new Column(value);
    }

    public Column add(final int addValue) {
        return new Column(this.value + addValue);
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
        Column column = (Column) o;
        return value == column.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
