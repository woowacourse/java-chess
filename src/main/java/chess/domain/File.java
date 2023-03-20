package chess.domain;

import java.util.Arrays;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    public static final int MIN_ORDER = 1;
    public static final int MAX_ORDER = 8;

    private final int order;

    File(final int order) {
        this.order = order;
    }

    public static File of(final int order) {
        return Arrays.stream(values())
                .filter(file -> file.order == order)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 file값을 입력하셨습니다."));
    }

    public int getOrder() {
        return order;
    }
}
