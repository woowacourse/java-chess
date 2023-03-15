package chess.domain.piece.position;

import java.util.Objects;
import java.util.regex.Pattern;

public class File {

    private static final Pattern pattern = Pattern.compile("^[a-h]$");
    private final char file;

    private File(final char file) {
        validate(file);
        this.file = file;
    }

    private void validate(final char file) {
        if (!pattern.matcher(String.valueOf(file)).matches()) {
            throw new IllegalArgumentException("a 에서 h 사이의 문자만 들어올 수 있습니다.");
        }
    }

    public static File from(final char file) {
        return new File(file);
    }

    public int interval(final File file) {
        return file.file - this.file;
    }

    public File plus(final int amount) {
        return File.from((char) (file + amount));
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
