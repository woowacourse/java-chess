package domain.position;

import java.util.Objects;

public class File {

    private final char letter;

    public File(char letter) {
        this.letter = letter;
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
