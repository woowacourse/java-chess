package chess;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(file, square.file) && Objects.equals(rank, square.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
