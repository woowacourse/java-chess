package chess.domain.position;

import java.util.Arrays;

public enum Column {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private static final String ERROR_INCORRECT_COLUMN = "올바르지 않은 컬럼입니다.";

    private final String value;
    private final int index;

    Column(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static Column of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_INCORRECT_COLUMN));
    }

    public static Column of(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.index == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_INCORRECT_COLUMN));
    }

    public int calculateDifference(Column column) {
        return this.index - column.index;
    }

    public boolean movable(int distance) {
        int moveIndex = this.index + distance;
        return moveIndex <= H.index && moveIndex >= A.index;
    }

    public Column move(int distance) {
        return of(index + distance);
    }

    public String getValue() {
        return value;
    }
}
