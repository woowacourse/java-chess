package domain.position;

import domain.direction.Direction;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return new Position(file, rank);
    }

    public static Position of(final String file, final String rank) {
        return new Position(File.of(file), Rank.of(rank));
    }

    public Position createNextPosition(final Direction direction) {
        int rank = this.getRank() + direction.getRank();
        int file = this.getFile() + direction.getFile();
        if (File.isFileRange(file) && Rank.isRankRange(rank)) {
            return Position.of(File.of(file), Rank.of(rank));
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    public int getRank() {
        return rank.getIndex();
    }

    public int getFile() {
        return file.getIndex();
    }

    public String getPosition() {
        return file.getSymbol() + rank.getIndex();
    }

    @Override
    public String toString() {
        return "Position{" +
            "file=" + file +
            ", rank=" + rank +
            '}';
    }
}
