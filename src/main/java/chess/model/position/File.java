package chess.model.position;

import java.util.Arrays;

public enum File {

    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int value;
    private final String name;

    File(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    public static File of(final int otherValue) {
        return Arrays.stream(values())
                .filter(file -> file.value == otherValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 값이 입력 되었습니다."));
    }

    public boolean canAdd(int row) {
        return value + row >= A.value && value + row <= H.value;
    }

    public File add(final int row) {
        return File.of(value + row);
    }

    public String nameOfFile() {
        return name;
    }
}
