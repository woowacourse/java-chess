package chess.domain.square;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.rank = rank;
        this.file = file;
    }

    public static Square from(final String square) {
        String[] splitSquare = square.split("");
        File file = File.from(splitSquare[0]);
        Rank rank = Rank.from(splitSquare[1]);
        return new Square(file, rank);
    }

    public Square move(int fileMoveUnit, int rankMoveUnit) {
        File newFile = file.move(fileMoveUnit);
        Rank newRank = rank.move(rankMoveUnit);
        return new Square(newFile, newRank);
    }

    public int getFileIndex() {
        return file.ordinal();
    }

    public int getRankIndex() {
        return rank.ordinal();
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
