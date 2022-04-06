package chess.chessboard.position;

import java.util.Arrays;

public enum File {

    A(1, 'a'),
    B(2, 'b'),
    C(3, 'c'),
    D(4, 'd'),
    E(5, 'e'),
    F(6, 'f'),
    G(7, 'g'),
    H(8, 'h');

    private final int value;
    private final char name;

    File(final int value, final char name) {
        this.value = value;
        this.name = name;
    }

    public File add(final int file) {
        return File.of(value + file);
    }

    public boolean canAdd(final int file) {
        return value + file >= A.value && value + file <= H.value;
    }

    public static File of(final int otherValue) {
        return Arrays.stream(values())
                .filter(file -> file.value == otherValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }

    public static File of(final char name) {
        return Arrays.stream(values())
                .filter(file -> file.name == name)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }

    public Character getName() {
        return this.name;
    }
}
