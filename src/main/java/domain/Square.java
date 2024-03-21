package domain;

import java.util.Objects;

public class Square {

    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;

    private final Rank rank;
    private final File file;

    public Square(final Rank rank, final File file) {
        this.file = file;
        this.rank = rank;
    }

    public static Square from(final String input) {
        final String file = String.valueOf(input.charAt(FILE_INDEX));
        final String rank = String.valueOf(input.charAt(RANK_INDEX));

        return new Square(Rank.from(rank), File.from(file));
    }

    public ChessVector calculateVector(final Square other) {
        final int rankSub = this.rank.subtract(other.rank);
        final int fileSub = this.file.subtract(other.file);

        return new ChessVector(fileSub, rankSub);
    }

    public Square next(final ChessVector chessVector) {
        final Rank newRank = this.rank.move(chessVector.y());
        final File newFile = this.file.move(chessVector.x());

        return new Square(newRank, newFile);
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
