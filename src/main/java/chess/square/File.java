package chess.square;

import java.util.Arrays;

public enum File {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    ;

    private final int value;

    File(int value) {
        this.value = value;
    }

    public boolean availableLocation(int distance) {
        return Arrays.stream(File.values())
                .anyMatch(file -> file.value == (this.value + distance));
    }

    public File nextTo(Integer value) {
        return Arrays.stream(File.values())
                .filter(file -> file.value == (this.value + value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
