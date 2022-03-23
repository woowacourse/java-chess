package chess.domain.board;

import java.util.Arrays;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8),
    ;

    private final String letter;
    private final int value;

    File(String letter, int value) {
        this.letter = letter;
        this.value = value;
    }

    public static File valueOf(int value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 값을 찾을 수 없습니다."));
    }
}
