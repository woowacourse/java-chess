package domain.position;

import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return new Position(file, rank);
    }

    public static Position of(String file, String rank) {
        return new Position(File.of(file), Rank.of(rank));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    public int getRank() {
        return rank.getIndex();
    }

    public int getFile() {
        return file.getIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Position{" +
            "row=" + rank +
            ", column=" + file +
            '}';
    }
}
