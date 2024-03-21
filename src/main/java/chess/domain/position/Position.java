package chess.domain.position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Position {

    public static final List<Position> ALL_POSITIONS = Arrays.stream(Rank.values())
            .flatMap(row -> Arrays.stream(File.values())
                    .map(column -> new Position(column, row)))
            .toList();

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = Objects.requireNonNull(file);
        this.rank = Objects.requireNonNull(rank);
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public int calculateFileDifference(Position position) {
        return this.file.calculateDifference(position.file);
    }

    public int calculateRankDifference(Position position) {
        return this.rank.calculateDifference(position.rank);
    }

    public Position moveToEast() {
        return new Position(file.toEast(), rank);
    }

    public Position moveToWest() {
        return new Position(file.toWest(), rank);
    }

    public Position moveToNorth() {
        return new Position(file, rank.toNorth());
    }

    public Position moveToSouth() {
        return new Position(file, rank.toSouth());
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
}
