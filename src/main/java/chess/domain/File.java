package chess.domain;

import java.util.Objects;

public class File {
    private static final int ASCII_LOWER_CASE_A = 97;
    private static final int ASCII_LOWER_CASE_H = 104;

    private final char file;

    public File(final int asciiValue) {
        this((char) asciiValue);
    }

    public File(final char file) {
        validate(file);
        this.file = file;
    }

    public static File from(final char file) {
        return new File(file);
    }

    private void validate(final char file) {
        if (file < ASCII_LOWER_CASE_A || ASCII_LOWER_CASE_H < file) {
            throw new IllegalArgumentException("기물의 세로 위치는 a부터 h까지 놓을 수 있습니다.");
        }
    }

    public int calculateDistance(final int file) {
        return this.file - file;
    }

    public char move(final int fileDirection) {
        return (char) (this.file + fileDirection);
    }

    public char getFile() {
        return this.file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file1 = (File) o;
        return file == file1.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }

    @Override
    public String toString() {
        return "File{" +
                "file=" + file +
                '}';
    }

}
