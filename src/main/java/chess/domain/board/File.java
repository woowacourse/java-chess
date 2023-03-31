package chess.domain.board;

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
    EMPTY(-1);

    private static final char OFFSET_LETTER = 'a';

    private final int value;

    File(final int value) {
        this.value = value;
    }

    public static File findFileByLetter(final char letter) {
        return findFile(letter - OFFSET_LETTER);
    }

    public static File findFile(final int fileIndex) {
        return Arrays.stream(File.values())
                .filter(file -> file.getValue() == fileIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 인덱스의 파일을 찾을 수 없습니다."));
    }

    public int getValue() {
        return value;
    }
}
