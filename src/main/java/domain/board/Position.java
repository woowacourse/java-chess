package domain.piece.info;

import java.util.Objects;

public class Position {
    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int fileIndex() {
        return file.index();
    }

    public int rankIndex() {
        return rank.index();
    }

    public Position goLeft() {
        return new Position(file.goLeft(), rank);
    }

    public Position goRight() {
        return new Position(file.goRight(), rank);
    }

    public Position goUp() {
        return new Position(file, rank.goUp());
    }

    public Position goDown() {
        return new Position(file, rank.goDown());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
