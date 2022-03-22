package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public enum Row {
    EIGHT("8"),
    SEVEN("7"),
    SIX("6"),
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1"),
    ;

    private static final String INVALID_RANGE = "유효하지 않은 범위입니다.";

    private final String value;

    Row(String value) {
        this.value = value;
    }

    public static Row of(String value) {
        return Arrays.stream(values())
            .filter(row -> row.value.equals(value))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(INVALID_RANGE));
    }

    public static List<Row> initialRows() {
        return List.of(EIGHT, SEVEN, TWO, ONE);
    }
}
