package chess.board;

import java.util.Arrays;

public enum File {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String value;
    private final int index;

    File(String value, int i) {
        this.value = value;
        this.index = i;
    }

    public static File find(String value) {
        return Arrays.stream(values())
                .filter(file -> file.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 체스판 범위를 벗어났습니다."));
    }

    public String getValue() {
        return value;
    }
}
