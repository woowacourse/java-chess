package chess.position;

import java.util.*;
import java.util.stream.Collectors;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int value;

    File(int value) {
        this.value = value;
    }

    public int getDistance(File other) {
        return Math.abs(this.value - other.value);
    }

    public static List<File> orderedValues() {
        return Arrays.stream(values())
            .sorted(Comparator.<File>comparingInt(row -> row.value).reversed())
            .collect(Collectors.toList());
    }
}
