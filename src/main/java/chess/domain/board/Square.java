package chess.domain.board;

import java.util.Objects;

public class Square {
    private final File file;
    private final Rank rank;

    public Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Square nextSquare(final Move move) {
        if (move == Move.UP_UP || move == Move.DOWN_DOWN) {
            final int nextFile = this.getX() + Integer.signum(move.getX());
            final int nextRank = this.getY() + Integer.signum(move.getY());

            return new Square(File.findFile(nextFile), Rank.findRank(nextRank));
        }

        final int nextFile = this.getX() + move.getX();
        final int nextRank = this.getY() + move.getY();

        return new Square(File.findFile(nextFile), Rank.findRank(nextRank));
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
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public int getX() {
        return file.getValue();
    }

    public int getY() {
        return rank.getValue();
    }

    public Rank rank() {
        return rank;
    }

    public File file() {
        return file;
    }
}
