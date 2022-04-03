package chess.domain.board;

import java.util.Arrays;

public enum File {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7),
    ;

    private final String value;
    private final int index;

    File(final String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static File from(final String value) {
        return Arrays.stream(values())
                .filter(file -> file.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 file 값입니다."));
    }

    public int calculateDifference(final File anotherFile, final boolean absoluteFlag) {
        final int difference = this.index - anotherFile.index;
        if (absoluteFlag) {
            return Math.abs(difference);
        }
        return difference;
    }

    public File next(final File targetFile) {
        if (this.index < targetFile.index) {
            return values()[this.index + 1];
        }
        if (this.index > targetFile.index) {
            return values()[this.index - 1];
        }
        return this;
    }
}
