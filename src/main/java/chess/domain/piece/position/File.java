package chess.domain.piece.position;

import java.util.Objects;

public class File {

    public static final char MIN = 'a';
    public static final char MAX = 'h';

    private final char value;

    private File(final char value) {
        validate(value);
        this.value = value;
    }

    private void validate(final char file) {
        if (file < MIN || file > MAX) {
            throw new IllegalArgumentException(
                    String.format("%c 에서 %c 사이의 문자만 들어올 수 있습니다.", MIN, MAX)
            );
        }
    }

    public static File from(final char file) {
        return new File(file);
    }

    public int interval(final File file) {
        return file.value - this.value;
    }

    public File plus(final int amount) {
        return File.from((char) (value + amount));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        final File file1 = (File) o;
        return value == file1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public char value() {
        return value;
    }
}
