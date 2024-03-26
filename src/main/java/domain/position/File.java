package domain.position;

import java.util.Objects;

public class File {
    public static final char MIN_LETTER = 'a';
    public static final char MAX_LETTER = 'h';
    private final char letter;

    public File(char letter) {
        validateLetterRange(letter);
        this.letter = letter;
    }

    private void validateLetterRange(char letter) {
        if (letter < MIN_LETTER || letter > MAX_LETTER) {
            throw new IllegalArgumentException();
        }
    }

    public File add(final int movement) {
        return new File((char) (letter + movement));
    }

    public int subtract(File target) {
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
