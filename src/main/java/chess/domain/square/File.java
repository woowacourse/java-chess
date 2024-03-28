package chess.domain.square;

import java.util.Arrays;

public enum File {
    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7),
    ;

    private static final String INVALID_FILE = "유효하지 않은 파일입니다. 알파벳 A~F 사이로 입력해주세요. (대소문자 무관)";

    private final int index;

    File(int index) {
        this.index = index;
    }

    public static File from(final String file) {
        return Arrays.stream(File.values())
                .filter(value -> value.name().equals(file.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE));
    }

    public File move(final int fileMoveStep) {
        return indexOf(this.index + fileMoveStep);
    }

    private File indexOf(final int index) {
        return Arrays.stream(values())
                .filter(file -> file.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE));
    }

    public int index() {
        return index;
    }
}
