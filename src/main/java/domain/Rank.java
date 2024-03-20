package domain;

import java.util.Arrays;

public enum Rank {

    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    ;

    private final int order;

    Rank(int order) {
        this.order = order;
    }

    public static int max() {
        return Arrays.stream(values())
                .mapToInt(it -> it.order)
                .max()
                .orElseThrow();
    }

    public static Rank find(int order) {
        return Arrays.stream(values())
                .filter(it -> it.order == order)
                .findFirst()
                .orElseThrow();
    }
}
