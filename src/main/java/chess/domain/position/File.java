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
    H("h");

    private final String file;

    File(final String file) {
        this.file = file;
    }

    public static File findByFile(final String file) {
        return Arrays.stream(values())
                .filter(value -> value.file.equals(file))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("없는 파일임! 입력 값: %s", file)));
    }
}
