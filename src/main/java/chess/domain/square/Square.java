package chess.domain.square;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Square next(int fileDirection, int rankDirection) {
        File newFile = file.next(fileDirection);
        Rank newRank = rank.next(rankDirection);
        return Squares.of(newFile, newRank);
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

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
    }
}
