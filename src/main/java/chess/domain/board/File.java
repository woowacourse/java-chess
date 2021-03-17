package chess.domain.board;

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

    public static File findValueOf(String fileInput) {
        return Arrays.stream(File.values())
            .filter(file -> file.getValue().equals(fileInput))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 file값 입니다."));
    }

    public String getValue() {
        return value;
    }
}
