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

    private static final String CAN_NOT_FIND_FILE = "[ERROR] 일치하는 File 값을 찾을 수 없습니다.";

    private final int value;

    File(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static File findFile(int value) {
        return Arrays.stream(File.values())
            .filter(file -> file.value == value)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(CAN_NOT_FIND_FILE));
    }
}
