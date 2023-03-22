package chess.domain.piece.position;

import java.util.Objects;

public class File {

    public static final char MIN = 'a';
    public static final char MAX = 'h';

    private final char file;

    public File(final char file) {
        validate(file);
        this.file = file;
    }

    private void validate(final char file) {
        if (file < MIN || file > MAX) {
            throw new IllegalArgumentException(
                    String.format("%c 에서 %c 사이의 문자만 들어올 수 있습니다.", MAX, MAX)
            );
        }
    }

    public int interval(final File file) {
        return file.file - this.file;
    }

    public File plus(final int amount) {
        return new File((char) (file + amount));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        final File file1 = (File) o;
        return file == file1.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }
}
