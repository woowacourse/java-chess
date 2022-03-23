package chess.domain.position;

import java.util.Arrays;

public enum Row {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String value;
    private final int index;

    Row(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static Row of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 로우입니다."));
    }

    public int calculateIndex(Row row) {
        return row.index - this.index;
    }
}
