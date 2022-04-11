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
    ONE(1);

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static Row find(int value) {
        return Arrays.stream(values())
                .filter(i -> i.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Row 값 입니다."));
    }

    public static Row find(char value) {
        int rowValue = Character.getNumericValue(value);
        return Arrays.stream(values())
                .filter(i -> i.value == rowValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Row 값 입니다."));
    }

    public int getDifference(Row row) {
        return this.value - row.value;
    }

    public String getSymbol() {
        return String.valueOf(value);
    }

    public Row plusRow(int number) {
        return find(value + number);
    }
}
