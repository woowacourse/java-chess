package domain.square;

import domain.ChessVector;

import java.util.Objects;

public class Square {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square from(final String input) {
        final String file = String.valueOf(input.charAt(FILE_INDEX));
        final int rank = input.charAt(RANK_INDEX) - '0';

        return new Square(File.from(file), Rank.from(rank));
    }

    public static Square of(final String fileName, final String rankName) {
        return new Square(File.valueOf(fileName), Rank.valueOf(rankName));
    }

    public ChessVector calculateVector(final Square other) {
        final int fileSub = this.file.subtract(other.file);
        final int rankSub = this.rank.subtract(other.rank);

        return new ChessVector(fileSub, rankSub);
    }

    public Square next(final ChessVector chessVector) {
        final File nextFile = this.file.move(chessVector.x());
        final Rank nextRank = this.rank.move(chessVector.y());

        return new Square(nextFile, nextRank);
    }

    public boolean isRank(final Rank rank) {
        return this.rank == rank;
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Square square = (Square) o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Square{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
