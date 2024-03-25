package chess.domain.square;

import java.util.Objects;

public class Square {
    private static final String SQUARE_DELIMITER = "";

    private final Rank rank;
    private final File file;

    private Square(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Square from(final String square) {
        String[] splitSquare = square.split(SQUARE_DELIMITER);
        Rank rank = Rank.from(splitSquare[1]);
        File file = File.from(splitSquare[0]);
        return new Square(rank, file);
    }

    public static Square of(final Rank rank, final File file) {
        return new Square(rank, file);
    }

    public int getRankIndex() {
        return rank.ordinal();
    }

    public int getFileIndex() {
        return file.ordinal();
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
