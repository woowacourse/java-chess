package chess.controller;

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

    private final String rawFile;
    private final int value;

    File(String rawFile, int value) {
        this.rawFile = rawFile;
        this.value = value;
    }

    public static int findFile(String input) {
        return Arrays.stream(File.values())
                .filter(file -> file.rawFile.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 열입니다."))
                .value;
    }
}
