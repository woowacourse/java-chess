package chess.domain.position;

import java.util.*;
import java.util.stream.Collectors;

public enum Column {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    Column(int value) {
        this.value = value;
    }

    public int getDistance(Column other) {
        return Math.abs(this.value - other.value);
    }

    public static List<Column> orderedValues() {
        return Arrays.stream(values())
            .sorted(Comparator.comparingInt(column -> column.value))
            .collect(Collectors.toList());
    }

    public static List<Column> reversOrderedValues() {
        return Arrays.stream(values())
            .sorted(Comparator.<Column>comparingInt(column -> column.value).reversed())
            .collect(Collectors.toList());
    }

    public List<Column> getPath(Column to) {
        int start = Math.min(this.value, to.value);
        int end = Math.max(this.value, to.value);

        if (this.value < to.value) {
            return getPath(orderedValues(), start, end);
        }
        return getPath(reversOrderedValues(), start, end);
    }

    private List<Column> getPath(List<Column> columns, int start, int end) {
        return columns.stream()
            .filter(file -> start < file.value && file.value < end)
            .collect(Collectors.toList());
    }
}
