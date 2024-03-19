package chess.domain;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.rank = rank;
        this.file = file;
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return rank == square.rank && file == square.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
