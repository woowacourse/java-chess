package chess.domain.board;

import java.util.Arrays;

public enum Column {

    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    private final int value;

    Column(final int value) {
        this.value = value;
    }

    public static Column from(final int value) {
        return Arrays.stream(values())
                .filter(column -> column.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 위치입니다."));
    }

    public int calculateDifference(final Column target) {
        return this.value - target.value;
    }

    public Column move(final int columnDifference) {
        return from(value + columnDifference);
    }
}
