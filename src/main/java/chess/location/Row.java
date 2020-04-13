package chess.location;

import java.util.Arrays;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static Row of(int value) {
        if (isInValidRange(value)) {
            throw new NoExistChessLocationException();
        }
        return Arrays.stream(Row.values())
                .filter(row -> row.value == value)
                .findAny()
                .get();
    }

    private static boolean isInValidRange(int value) {
        return ONE.value > value || EIGHT.value < value;
    }

    public boolean is(Row row) {
        return this.value == row.value;
    }

    public boolean isHigherThan(Row row) {
        return this.value > row.value;
    }

    public Row plus(int row) {
        return Row.of(this.value + row);
    }

    public int getValue() {
        return value;
    }
}
