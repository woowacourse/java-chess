package chess.domain.square;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(final File file, final Rank rank) {
        return new Square(file, rank);
    }

    public Square next(int fileDirection, int rankDirection) {
        File newFile = file.next(fileDirection);
        Rank newRank = rank.next(rankDirection);
        return Squares.getSquare(newFile, newRank);
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
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return String.join(" ", file.name(), rank.name());
    }
}
