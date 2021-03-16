package chess.domain;

import java.util.Arrays;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h");

    private final String file;

    File(String file) {
        this.file = file;
    }

    public static File of(String file) {
        return Arrays.stream(values())
            .filter(value -> file.equals(value.file))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 범위를 벗어났습니다."));
    }

    public String getFile() {
        return file;
    }
}
