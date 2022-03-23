package chess.domain.board;

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

    private final String name;
    private final int value;

    Column(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    private static Column of(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 Column 입니다."));
    }

    public Column move(final int horizon) {
        return of(this.value + horizon);
    }

    public int subtract(final Column column) {
        return column.value - this.value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
