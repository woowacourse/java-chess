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

        return getBetweenPositions(other, fileGap, rankGap);
    }

    private List<Position> getBetweenPositions(Position other, int fileGap, int rankGap) {
        int distance = Math.max(Math.abs(fileGap), Math.abs(rankGap));
        int fileUnitGap = fileGap / distance;
        int rankUnitGap = rankGap / distance;

        List<Position> between = new ArrayList<>();
        for (int i = 1; i < distance; i++) {
            other = other.move(fileUnitGap, rankUnitGap);
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
