package chess.domain.position;

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
    private final String text;

    File(final int value, final String text) {
        this.value = value;
        this.text = text;
    }

    public static File of(String text) {
        return Arrays.stream(values())
                .filter(file -> file.text.equals(text))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 파일 값이 존재하지 않습니다."));
    }

    public static File of(int value) {
        return Arrays.stream(values())
                .filter(file -> file.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 파일 값이 존재하지 않습니다."));
    }

    public int getValuePoint(File targetFile) {
        if (targetFile.value - this.value == 0) {
            return 0;
        }
        if (targetFile.value - this.value > 0) {
            return 1;
        }
        return -1;
    }

    public int getValueDiff(File targetFile) {
        return Math.abs(targetFile.value - this.value);
    }

    public static int length() {
        return values().length;
    }

    public int getValue() {
        return value;
    }
}
