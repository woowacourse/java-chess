package chess;

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

    private final String value;

    File(String value) {
        this.value = value;
    }

    public static File of(String input) {
        return Arrays.stream(values())
            .filter(file -> file.value.equals(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재 하지 않는 파일입니다."));
    }
}
