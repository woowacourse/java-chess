package chess.domain.position;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private final int file;

    File(int file) {
        this.file = file;
    }

    public int calculateDifference(File file) {
        return file.file - this.file;
    }

    public File move(int moveUnit) {
        int destination = file + moveUnit;
        return Arrays.stream(File.values()).filter(start -> start.file == destination)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 file 값입니다."));
    }
}
