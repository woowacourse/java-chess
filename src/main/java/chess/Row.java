package chess;

import java.util.*;
import java.util.stream.Collectors;

public enum Row {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<Row> orderedValues() {
        return Arrays.stream(values())
            .sorted(Comparator.<Row>comparingInt(row -> row.value).reversed())
            .collect(Collectors.toList());
    }
}
