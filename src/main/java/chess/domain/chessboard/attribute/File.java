package chess.domain.chessboard.attribute;

import java.util.Arrays;
import java.util.function.Predicate;

public enum File {
    A, B, C, D, E, F, G, H;

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
                        "파일은 a~h 사이로 입력해주세요: %s".formatted(value)));
    }

    public static boolean isInRange(final int column) {
        return A.toColumn() <= column && column <= H.toColumn();
    }

    public int toColumn() {
        return ordinal();
    }
}
