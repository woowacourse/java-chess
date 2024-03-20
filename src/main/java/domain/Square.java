package domain;

import java.util.Objects;

public class Square {

    private final Rank rank;
    private final File file;

    public Square(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public Rank getRank() {
        return rank;
    }

    public Square next(final int rankVector, final int fileVector) {
        return new Square(rank.move(rankVector), file.move(fileVector));
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Square square = (Square) o;
        return rank == square.rank && file == square.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Square{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }
}
