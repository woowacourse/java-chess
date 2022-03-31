package chess.domain.board;

import java.util.Arrays;

public enum File {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e"),
    F("f"),
    G("g"),
    H("h"),
    ;

    private final String value;

    File(final String value) {
        this.value = value;
    }

    public static File from(final String value) {
        return Arrays.stream(values())
                .filter(file -> file.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 file 값입니다."));
    }

    public int calculateDifference(final File anotherFile, final boolean absoluteFlag) {
        final int difference = this.ordinal() - anotherFile.ordinal();
        if (absoluteFlag) {
            return Math.abs(difference);
        }
        return difference;
    }

    public File next(final File targetFile) {
        if (this.ordinal() < targetFile.ordinal()) {
            return values()[this.ordinal() + 1];
        }
        if (this.ordinal() > targetFile.ordinal()) {
            return values()[this.ordinal() - 1];
        }
        return this;
    }
}
