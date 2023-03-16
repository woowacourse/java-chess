package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Position {
    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public int calculateRankGap(Position other) {
        return rank.subtractOrder(other.rank);
    }

    public int calculateFileGap(Position other) {
        return file.subtractOrder(other.file);
    }

    public List<Position> getBetweenPositions(Position other) {
        int fileGap = this.calculateFileGap(other);
        int rankGap = this.calculateRankGap(other);

        int distance = Math.max(Math.abs(fileGap), Math.abs(rankGap));

        int i1 = fileGap / distance;
        int i2 = rankGap / distance;

        List<Position> between = new ArrayList<>();
        for (int i = 1; i < distance; i++) {
            other = other.move(i1, i2);
            between.add(other);
        }

        return between;
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

    public Position move(int fileStep, int rankStep) {
        File newFile = file.move(fileStep);
        Rank newRank = rank.move(rankStep);
        return new Position(newFile, newRank);
    }
}
