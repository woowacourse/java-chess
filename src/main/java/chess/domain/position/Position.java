package chess.domain.position;

import java.util.Objects;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public int calculateRankDiff(Position position) {
        return rank.calculateDiff(position.rank);
    }

    public int calculateFileDiff(Position position) {
        return file.calculateDiff(position.file);
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

    public int getRankValue() {
        return rank.getValue();
    }

    public int getFileValue() {
        return file.getValue();
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
