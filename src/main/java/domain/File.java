package domain;

import java.util.Arrays;
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

    public static int max() {
        return Arrays.stream(values())
                .mapToInt(it -> it.order)
                .max()
                .orElseThrow();
    }

    public static File find(int order) {
        return Arrays.stream(values())
                .filter(it -> it.order == order)
                .findFirst()
                .orElseThrow();
    }

    public boolean confirmGap(File other, int gapSize) {
        return gap(other) == gapSize;
    }

    public int gap(File other) {
        int otherOrder = other.order;
        return Math.abs(order - otherOrder);
    }

    public List<File> findBetween(File target) {
        if (this.order > target.order) {
            return getFiles(target, this);
        }
        return getFiles(this, target);
    }

    private List<File> getFiles(File a, File b) {
        return Arrays.stream(values())
                .filter(file -> file.order > a.order && file.order < b.order)
                .toList();
    }
}
