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

    private final int x;

    File(final int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public static File findFile(final int targetFile) {
        return Arrays.stream(File.values())
                .filter(file -> file.getX() == targetFile)
                .findFirst()
                .orElseThrow();
    }
}
