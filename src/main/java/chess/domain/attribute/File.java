package chess.domain.attribute;

import java.util.Arrays;
import java.util.function.Predicate;

public enum File {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final int FILE_MIN = 1;
    private static final int FILE_MAX = 8;

    private final int value;

    File(final int value) {
        this.value = value;
    }

    public static File of(final char value) {
        return of(String.valueOf(value));
    }

    public static File of(final int value) {
        return findByColumn(value, file -> file.value == value);
    }

    public static File of(final String value) {
        return findByColumn(value, file -> value.equalsIgnoreCase(file.name()));
    }

    private static <T> File findByColumn(final T column, final Predicate<File> predicate) {
        return Arrays.stream(values())
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "파일은 %d~%d 사이로 입력해주세요: %s".formatted(FILE_MIN, FILE_MAX, column)));
    }

    public File left() {
        return left(1);
    }

    public File left(int step) {
        int leftOrdinal = this.ordinal() - step;
        File[] files = File.values();
        try {
            return files[leftOrdinal];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalStateException("해당 칸의 왼쪽 칸이 존재하지 않습니다.");
        }
    }

    public File right() {
        return right(1);
    }

    public File right(int step) {
        int rightOrdinal = this.ordinal() + step;
        File[] files = File.values();
        try {
            return files[rightOrdinal];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalStateException("해당 칸의 오른쪽 칸이 존재하지 않습니다.");
        }
    }

    public boolean canMoveLeft() {
        return canMoveLeft(1);
    }

    public boolean canMoveLeft(int step) {
        try {
            left(step);
        } catch (IllegalStateException exception) {
            return false;
        }
        return true;
    }

    public boolean canMoveRight() {
        return canMoveRight(1);
    }

    public boolean canMoveRight(int step) {
        try {
            right(step);
        } catch (IllegalStateException exception) {
            return false;
        }
        return true;
    }
}
