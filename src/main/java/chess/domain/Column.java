package chess.domain;

import java.util.Arrays;

public enum Column {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    private final int index;

    Column(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Column calculateNextColumn(int distance) {
        return Arrays.stream(values())
                .filter(column -> column.index == this.index + distance)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 Column 가 없습니다."));
    }

    public boolean isNextInRange(int distance) {
        int nextIndex = index + distance;
        return A.index <= nextIndex && nextIndex <= H.index;
    }
}
