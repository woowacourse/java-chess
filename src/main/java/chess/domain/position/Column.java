package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Column {
    ONE("1", 7),
    TWO("2", 6),
    THREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0);

    private final String value;
    private final int index;

    Column(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static Column of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equalsIgnoreCase(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 컬럼입니다."));
    }

    public static List<Column> reverseColumns() {
        List<Column> columns = Arrays.asList(values());
        columns.sort(Collections.reverseOrder());
        return columns;
    }

    public int calculateIndex(Column column) {
        return this.index - column.index;
    }
}
