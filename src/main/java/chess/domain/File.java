package chess.domain;

import java.util.Objects;
import java.util.Optional;

public class File {

    private static final char MINIMUM_FILE = 'a';
    private static final char MAXIMUM_FILE = 'h';

    private final char file;

    public File(char file) {
        validateRange(file);
        this.file = file;
    }

    private void validateRange(char file) {
        if (file < MINIMUM_FILE || file > MAXIMUM_FILE) {
            throw new IllegalArgumentException(
                    String.format("가로 위치는 %c ~ %c 사이의 값이어야 합니다.", MINIMUM_FILE, MAXIMUM_FILE));
        }
    }

    public int distance(File file) {
        return this.file - file.file;
    }

    public Optional<File> add(int directionOfFile) {
        try {
            char file = (char) (this.file + directionOfFile);
            return Optional.of(new File(file));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        File file1 = (File) o;
        return Objects.equals(file, file1.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }
}
