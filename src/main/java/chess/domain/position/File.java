package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static int length() {
        return values().length;
    }

    public int gap(File other) {
        return Math.abs(order - other.order);
    }

    public List<File> findBetween(File target) {
        if (this.order > target.order) {
            List<File> files = makeBetween(targetToSource(target));
            Collections.reverse(files);
            return files;
        }
        return makeBetween(sourceToTarget(target));
    }

    private List<File> makeBetween(Predicate<File> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private Predicate<File> targetToSource(File other) {
        return file -> file.order < this.order && file.order > other.order;
    }

    private Predicate<File> sourceToTarget(File other) {
        return file -> file.order > this.order && file.order < other.order;
    }
}
