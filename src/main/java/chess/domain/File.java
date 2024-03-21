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
        if (isOutOfRange(file)) {
            throw new IllegalArgumentException(
                    String.format("가로 위치는 %c ~ %c 사이의 값이어야 합니다.", MINIMUM_FILE, MAXIMUM_FILE));
        }
    }

    private boolean isOutOfRange(char file) {
        return file < MINIMUM_FILE || file > MAXIMUM_FILE;
    }

    public int distance(File file) {
        return this.file - file.file;
    }

    public File add(int directionOfFile) {
        char file = (char) (this.file + directionOfFile);
        return new File(file);
    }

    public boolean addable(int addFile) {
        return !isOutOfRange((char) (this.file + addFile));
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
