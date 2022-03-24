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
    H("h"),
    ;

    private final String file;

    File(String file) {
        this.file = file;
    }

    public static File of(String text) {
        return Arrays.stream(File.values())
                .filter(file -> file.file.equals(text))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 축은 없습니다."));
    }
}
