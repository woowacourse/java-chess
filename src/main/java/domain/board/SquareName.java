package domain.board;

import java.util.Objects;

public final class SquareName {
    private final String squareName;

    public SquareName(String squareName) {
        this.squareName = squareName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SquareName that = (SquareName) o;
        return Objects.equals(squareName, that.squareName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squareName);
    }
}
