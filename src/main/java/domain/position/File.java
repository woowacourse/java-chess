package domain.position;

import java.util.Objects;

public class File {
    public static final char START_LETTER = 'a';
    public static final char END_LETTER = 'h';
    private final char letter;

    public File(final char letter) {
        validate(letter);
        this.letter = letter;
    }

    private void validate(final char letter) {
        if (isInvalidLetter(letter)) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 File 문자 입력입니다.");
        }
    }

    private boolean isInvalidLetter(final char letter) {
        return letter < START_LETTER || letter > END_LETTER;
    }

    public File add(final int movement) {
        return new File((char) (letter + movement));
    }

    public int subtract(final File target) {
        return letter - target.letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        File file = (File) o;
        return letter == file.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }
}
