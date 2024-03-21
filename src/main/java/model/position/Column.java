package model.position;

import java.util.Arrays;

public enum Column {

    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7),
    ;

    private final String value;
    private final int index;

    Column(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static Column from(char input) {
        return Arrays.stream(values())
                .filter(column -> column.value.equals(String.valueOf(input)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 column값을 입력했습니다."));
    }

    public static Column from(int targetIndex) {
        return Arrays.stream(values())
                .filter(column -> column.index == targetIndex)
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
