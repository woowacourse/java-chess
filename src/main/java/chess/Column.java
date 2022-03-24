package chess;

import java.util.Arrays;

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

    Column(final int value) {
        this.value = value;
    }

    public static Column of(final String value) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다."));
    }
}
