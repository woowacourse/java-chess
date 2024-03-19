package domain;

import java.util.Objects;

public class Square {
    private final Rank rank;
    private final File file;

    public Square(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Square square = (Square) o;
        return rank == square.rank && file == square.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
