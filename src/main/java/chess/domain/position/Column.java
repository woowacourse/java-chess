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
            .sorted(Comparator.comparingInt(row -> row.value))
            .collect(Collectors.toList());
    }

    public List<Column> getPath(Column to) {
        int start = Math.min(this.value, to.value);
        int end = Math.max(this.value, to.value);

        return orderedValues().stream()
            .filter(file -> start < file.value && file.value < end)
            .collect(Collectors.toList());
    }
}
