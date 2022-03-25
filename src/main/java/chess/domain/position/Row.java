package chess.domain.position;

import java.util.Arrays;

public enum Row {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

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
