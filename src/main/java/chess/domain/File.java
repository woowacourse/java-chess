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

    private final String fileName;

    File(String fileName) {
        this.fileName = fileName;
    }

    public static File from(String fileName) {
        return Arrays.stream(File.values())
                .filter(it -> it.fileName.equals(fileName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 file 입니다"));
    }
}
