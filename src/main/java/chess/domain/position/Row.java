package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    Row(int value) {
        this.value = value;
    }

    public static List<Row> orderedValues() {
        return Arrays.stream(values())
            .sorted(Comparator.<Row>comparingInt(col -> col.value).reversed())
            .collect(Collectors.toList());
    }

    public boolean isDownward(Row destination) {
        return value > destination.value;
    }

    public boolean isUpward(Row destination) {
        return value < destination.value;
    }

    public int getDistance(Row row) {
        return Math.abs(this.value - row.value);
    }

    public List<Row> getPath(Row to) {
        int start = Math.min(this.value, to.value);
        int end = Math.max(this.value, to.value);

        return orderedValues().stream()
            .filter(row -> start < row.value && row.value < end)
            .collect(Collectors.toList());
    }

    public int getValue() {
        return value;
    }
}
