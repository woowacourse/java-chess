package domain.position;

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

    private final int index;

    Row(final int index) {
        this.index = index;
    }

    public static Row of(int index) {
        return Arrays.stream(values())
            .filter(value -> value.index == index)
            .findFirst()
            .orElse(null);
    }

    public static boolean isRowRange(int index) {
        return ONE.index <= index && index <= EIGHT.index;
    }

    public int getIndex() {
        return index;
    }
}
