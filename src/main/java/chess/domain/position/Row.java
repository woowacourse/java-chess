package chess.domain.position;

import java.util.Arrays;

public enum Row {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1),
    ;

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    public static Row of(final int value) {
        return Arrays.stream(Row.values())
                .filter(row -> row.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Row 값 입니다."));
    }

    public static Row of(final String value) {
        return Row.of(toRowValue(value));
    }

    private static int toRowValue(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 Row 값 입니다.");
        }
    }

    public int calculateDistance(final Row row) {
        return value - row.value;
    }

    public Row move(final int distance) {
        try {
            return of(value + distance);
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    public String getName() {
        return String.valueOf(value);
    }
}
