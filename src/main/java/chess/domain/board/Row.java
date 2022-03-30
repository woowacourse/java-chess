package chess.domain.board;

import java.util.Arrays;

public enum Row {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    private static Row of(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 Row 입니다."));
    }

    public Row move(final int vertical) {
        return of(this.value + vertical);
    }

    public int subtract(final Row row) {
        return row.value - this.value;
    }

    public int getValue() {
        return value;
    }
}
