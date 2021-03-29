package chess.domain.position;

import java.util.Arrays;

public enum File {
    EMPTY(0, ""),
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int value;
    private final String file;

    File(final int value, final String file) {
        this.value = value;
        this.file = file;
    }

    public static File findByValue(final int target) {
        return Arrays.stream(values())
                .filter(value -> value.value == target)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("없는 파일임! 입력 값: %d", target)));
    }

    public static File findByFile(final String file) {
        return Arrays.stream(values())
                .filter(value -> value.file.equals(file))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("없는 파일임! 입력 값: %s", file)));
    }

    public boolean isSameFile(final File file) {
        return this == file;
    }

    public String getFile() {
        return file;
    }

    public int getValue() {
        return value;
    }
}
