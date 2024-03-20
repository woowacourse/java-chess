package domain.board;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position nextPosition(Direction direction) {
        return new Position(new File(file.value() + direction.getFileDirection()),
            new Rank(rank.value() + direction.getRankDirection()));
    }

    public boolean isPawnInitialPosition() {
        return rank.isRankTwo();
    }

    public int calculateFileDifference(Position otherPosition) {
        return file.subtract(otherPosition.file);
    }

    public int calculateRankDifference(Position otherPosition) {
        return rank.subtract(otherPosition.rank);
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
        return Objects.equals(file, position.file) && Objects.equals(rank, position.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
            "file=" + file +
            ", rank=" + rank +
            '}';
    }

    public List<Position> findStraightRoutePositions(Position targetPosition) {
        if (this.calculateFileDifference(targetPosition) == 0) {
            int minRank = Math.min(rank.value(), targetPosition.rank.value());
            int maxRank = Math.max(rank.value(), targetPosition.rank.value());
            return IntStream.range(minRank, maxRank)
                .mapToObj(rank -> new Position(file, new Rank(rank)))
                .toList();
        }

        if (this.calculateRankDifference(targetPosition) == 0) {
            int minFile = Math.min(file.value(), targetPosition.file.value());
            int maxFile = Math.max(file.value(), targetPosition.file.value());
            return IntStream.range(minFile, maxFile)
                .mapToObj(file -> new Position(new File(file), rank))
                .toList();
        }

        throw new IllegalArgumentException("직선 이동이 아닙니다.");
    }
}
