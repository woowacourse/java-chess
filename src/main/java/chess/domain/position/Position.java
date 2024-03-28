package chess.domain.position;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public int rankDistance(Position position) {
        return rank.calculateDistance(position.rank);
    }

    public int subtractRank(Position position) {
        return rank.subtract(position.rank);
    }

    public int fileDistance(Position position) {
        return file.calculateDistance(position.file);
    }

    public int subtractFile(Position position) {
        return file.subtract(position.file);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (rank != position.rank) return false;
        return file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Position{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }

    public int rankValue() {
        return rank.getValue();
    }

    public int fileValue() {
        return file.getValue();
    }

    public Rank rank() {
        return rank;
    }

    public File file() {
        return file;
    }
}
