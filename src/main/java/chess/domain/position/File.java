package chess.domain.position;

import java.util.Arrays;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h")
    ;

    private final String notation;

    File(String notation) {
        this.notation = notation;
    }

    public static File of(String value) {
        return Arrays.stream(File.values())
                .filter(file -> file.notation.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일입니다."));
    }
}
