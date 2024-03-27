package model.position;

import java.util.Arrays;

public enum Row {

    EIGHTH("8", 0),
    SEVENTH("7", 1),
    SIXTH("6", 2),
    FIFTH("5", 3),
    FOURTH("4", 4),
    THIRD("3", 5),
    SECOND("2", 6),
    FIRST("1", 7);

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

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
