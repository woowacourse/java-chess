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

    private final int value;

    File(final int value) {
        this.value = value;
    }

    static File from(int value) {
        return Arrays.stream(values())
                .filter(it -> it.value == value)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    int gapWith(File file) {
        return this.value - file.value;
    }

    public int value() {
        return value;
    }
}
