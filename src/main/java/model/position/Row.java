package model.position;

import java.util.Arrays;

public enum Row {

    ONE("1", 7),
    TWO("2", 6),
    THREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0);

    private final String value;
    private final int index;

    Row(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static Row from(char input) {
        return Arrays.stream(values())
                .filter(row -> row.value.equals(String.valueOf(input)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 row값을 입력했습니다."));
    }

    public static Row from(int targetIndex) {
        return Arrays.stream(values())
                .filter(row -> row.index == targetIndex)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("인덱스 범위 초과"));
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
