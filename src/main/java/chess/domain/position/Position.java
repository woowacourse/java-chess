package chess.domain.position;

import java.util.Objects;

public final class Position {
    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int calculateRankGap(Position other) {
        Rank otherRank = other.rank;

        return rank.subtractOrder(otherRank);
    }

    public int calculateFileGap(Position other) {
        File otherFile = other.file;

        return file.subtractOrder(otherFile);
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
