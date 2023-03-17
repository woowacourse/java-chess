package chess.domain.board.position;

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

    public static File from(int value) {
        return Arrays.stream(values())
                     .filter(it -> it.value == value)
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 File 방향입니다."));
    }

    public int differenceBetween(File other) {
        return value - other.value;
    }

    public int value() {
        return value;
    }
}
