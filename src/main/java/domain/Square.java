package domain;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Square next(final Direction direction) {
        final File newFile = this.file.move(direction.row());
        final Rank newRank = this.rank.move(direction.column());

        return new Square(newFile, newRank);
    }

    public boolean canMove(final Direction direction) {
        return file.canMove(direction.row()) && rank.canMove(direction.column());
    }

    public boolean isRank(final Rank rank) {
        return this.rank == rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Square square)) {
            return false;
        }
        return rank == square.rank && file == square.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Square{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
