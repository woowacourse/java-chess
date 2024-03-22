package domain;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public ChessVector calculateVector(final Square other) {
        final int rankSub = this.rank.subtract(other.rank);
        final int fileSub = this.file.subtract(other.file);

        return new ChessVector(fileSub, rankSub);
    }

    public Square next(final ChessVector chessVector) {
        final Rank newRank = this.rank.move(chessVector.y());
        final File newFile = this.file.move(chessVector.x());

        return new Square(newFile, newRank);
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
