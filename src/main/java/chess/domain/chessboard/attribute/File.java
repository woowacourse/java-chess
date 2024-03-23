package chess.domain.chessboard.attribute;

import java.util.Arrays;
import java.util.function.Predicate;

public enum File {
    A, B, C, D, E, F, G, H;

    private static final int FILE_MIN = 1;
    private static final int FILE_MAX = 8;

    public static File from(final char value) {
        return from(String.valueOf(value));
    }

    public static File from(final String value) {
        return of(value, file -> value.equalsIgnoreCase(file.name()));
    }

    public static File from(final int column) {
        return of(column, file -> column == file.toColumn());
    }

    private static <T> File of(final T value, final Predicate<File> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "파일은 %d~%d 사이로 입력해주세요: %s".formatted(FILE_MIN, FILE_MAX, value)));
    }

    public static boolean isInRange(final int column) {
        return FILE_MIN <= column && column <= FILE_MAX;
    }

    public int toColumn() {
        return ordinal();
    }
}
