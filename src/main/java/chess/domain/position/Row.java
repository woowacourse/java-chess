package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Row {

    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8);

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

    public static Row of(final int value) {
        return Arrays.stream(values())
                .filter(it -> it.index == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 로우입니다."));
    }

    public static List<Row> reverseRows() {
        List<Row> rows = Arrays.asList(values());
        rows.sort(Collections.reverseOrder());
        return rows;
    }

    public int calculateGap(Row row) {
        return row.index - this.index;
    }

    public boolean isMovable(int distance) {
        int moveIndex = this.index + distance;
        return moveIndex <= EIGHT.index && moveIndex >= ONE.index;
    }

    public Row move(int distance) {
        return of(index + distance);
    }

    public String getValue() {
        return value;
    }
}
