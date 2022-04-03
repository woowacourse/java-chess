package chess.domain.board.position;

import java.util.Arrays;

public enum File {

    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8),
    ;

    private final String value;
    private final int order;

    File(final String value, final int order) {
        this.value = value;
        this.order = order;
    }

    public static File from(final String value) {
        return Arrays.stream(values())
                .filter(file -> file.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 file 값입니다."));
    }

    private static File from(final int order) {
        return Arrays.stream(values())
                .filter(file -> file.order == order)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 file 값입니다."));
    }

    public int calculateDifference(final File anotherFile) {
        return this.order - anotherFile.order;
    }

    public File next(final File targetFile) {
        if (this.order < targetFile.order) {
            return File.from(this.order + 1);
        }
        if (this.order > targetFile.order) {
            return File.from(this.order - 1);
        }
        return this;
    }

    @Override
    public String toString() {
        return value;
    }
}
