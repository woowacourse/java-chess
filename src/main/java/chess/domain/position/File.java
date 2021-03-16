package chess.domain.position;

import java.util.Arrays;

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

    public static File findByFile(final int file) {
        return Arrays.stream(values())
                .filter(value -> value.value == file)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("없는 파일임! 입력 값: %d", file)));
    }
}
