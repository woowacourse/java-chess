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
    H(7);

    private static final char DIFFERENCE_BETWEEN_LETTER_AND_INDEX = 'a';

    private final int x;

    File(final int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public static File findFileByLetter(final char letter) {
        return findFile(letter - DIFFERENCE_BETWEEN_LETTER_AND_INDEX);
    }

    public static File findFile(final int fileIndex) {
        return Arrays.stream(File.values())
                .filter(file -> file.getX() == fileIndex)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 좌표를 찾을 수 없습니다."));
    }
}
