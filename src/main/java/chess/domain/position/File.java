package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

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

    public File move(int step) {
        return Arrays.stream(File.values())
                .filter(it -> it.order == this.order + step)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public int subtractOrder(File other) {
        return this.order - other.order;
    }
}
