package chess.domain.position;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    ;

    private final int order;

    File(int order) {
        this.order = order;
    }

    public static int length() {
        return values().length;
    }

    public int gap(File other) {
        return Math.abs(order - other.order);
    }

    public List<File> findBetween(File other) {
        if (this.order < other.order) {
            return betweenAcs(other);
        }
        return betweenDesc(other);
    }

    private List<File> betweenAcs(File other) {
        return Arrays.stream(values())
                .filter(file -> file.order > this.order && file.order < other.order)
                .toList();
    }

    private List<File> betweenDesc(File other) {
        return Arrays.stream(values())
                .filter(file -> file.order > other.order && file.order < this.order)
                .sorted(Comparator.comparingInt(File::ordinal).reversed())
                .toList();
    }
}
