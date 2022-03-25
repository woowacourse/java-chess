package chess.domain.piece.position;

import java.util.Arrays;

public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8),
    Out(-1);

    private final int value;

    File(int value) {
        this.value = value;
    }

    private static File valueOf(int value) {
        return Arrays.stream(File.values())
            .filter(file -> file.value == value)
            .findFirst()
            .get();
    }

    public File getNext(int next) {
        if (value + next < 1 || value + next > 8) {
            return Out;
        }

        return File.valueOf(value + next);
    }
}
