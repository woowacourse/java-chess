package chess.board;

import java.util.Arrays;

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

    private final int index;

    File(final int index) {
        this.index = index;
    }

    public static File of(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File의 index는 1~8이여야합니다."));
    }

    public int getIndex() {
        return index;
    }
}
