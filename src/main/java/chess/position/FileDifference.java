package chess.position;

import java.util.Objects;

public class FileDifference {

    private final int difference;

    public FileDifference(int difference) {
        this.difference = difference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileDifference that = (FileDifference) o;
        return difference == that.difference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(difference);
    }
}
