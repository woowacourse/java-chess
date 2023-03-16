package chess.board;

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

    public File getAddedFile(int valueToAdd) {
        final int addedFileValue = this.value + valueToAdd;
        return Arrays.stream(values())
                .filter(file -> file.value == addedFileValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 더한 파일 값이 존재하지 않습니다."));
    }

    public static File findByValue(int value) {
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
