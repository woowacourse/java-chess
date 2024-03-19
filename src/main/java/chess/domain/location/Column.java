package chess.domain.location;

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

    private final int index;

    Column(int index) {
        this.index = index;
    }

    public static Column of(String input) {
        return Arrays.stream(values())
                .filter(column -> column.isName(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Column 입력입니다."));
    }

    private boolean isName(String name) {
        return this.name().equalsIgnoreCase(name);
    }

    public int calculateDistance(Column other) {
        return other.index - this.index;
    }
}
