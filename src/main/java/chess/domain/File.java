package chess.domain;

import java.util.Objects;

class File {

    private static final char MIN_VALUE_RANGE = 'a';
    private static final char MAX_VALUE_RANGE = 'h';

    private final char value;

    public File(char value) {
        if (value < MIN_VALUE_RANGE || value > MAX_VALUE_RANGE) {
            throw new IllegalArgumentException("유효한 범위의 알파벳이 아닙니다.");
        }

        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return value == file.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
