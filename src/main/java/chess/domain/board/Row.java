package chess.domain.board;

import java.util.Arrays;

public enum Row {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3),
    FIVE(4),
    SIX(5),
    SEVEN(6),
    EIGHT(7),
    ;

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public Row flip() {
        return Arrays.stream(Row.values())
                .filter(it -> it.value == (7 - this.value))
                .findFirst()
                .orElseThrow();
    }
}
