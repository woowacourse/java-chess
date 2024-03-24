package chess.domain.square;

import java.util.Arrays;

public enum File {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    ;

    private static final String INVALID_FILE = "일치하지 않은 파일 입니다.";

    public static File from(final String file) {
        return Arrays.stream(File.values())
                .filter(value -> value.name().equals(file.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE));
    }

    public File from(final int fileMoveStep) {
        return indexOf(this.ordinal() + fileMoveStep);
    }

    private File indexOf(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.ordinal() == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE));
    }
}
