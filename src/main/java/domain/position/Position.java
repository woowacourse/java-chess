package domain.position;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Position {

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return new Position(file, rank);
    }

    public List<Position> between(final Position destination) {
        final Direction direction = Direction.of(this, destination);

        if (Direction.NOTHING.equals(direction)) {
            return Collections.emptyList();
        }

        return getBetweenPositions(destination, direction);
    }

    private List<Position> getBetweenPositions(final Position destination, final Direction direction) {
        final int countBetweenPositions = getMaxDifference(destination) - 1;

        return Stream.iterate(this.move(direction), position -> position.move(direction))
                .limit(countBetweenPositions)
                .collect(Collectors.toUnmodifiableList());
    }

    private int getMaxDifference(final Position destination) {
        final int rankDifference = Math.abs(getRankDifference(destination));
        final int fileDifference = Math.abs(getFileDifference(destination));

        return Math.max(rankDifference, fileDifference);
    }

    public int getRankDifference(final Position other) {
        return rank.getDifference(other.rank);
    }

    public int getFileDifference(final Position other) {
        return file.getDifference(other.file);
    }

    public int getDistance(final Position other) {
        return Math.abs(getFileDifference(other)) + Math.abs(getRankDifference(other));
    }

    public Position move(final Direction direction) {
        final Rank movedRank = rank.move(direction.getRankDifference());
        final File movedFile = file.move(direction.getFileDifference());

        return Position.of(movedFile, movedRank);
    }

    public int compareRank(final Position other) {
        final String otherRank = other.rank.getName();
        final String thisRank = rank.getName();
        final int rankDifference = otherRank.compareTo(thisRank);

        return Integer.compare(rankDifference, 0);
    }

    public int compareFile(final Position other) {
        final String otherFile = other.file.getName();
        final String thisFile = file.getName();
        final int fileDifference = otherFile.compareTo(thisFile);

        return Integer.compare(fileDifference, 0);
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
