package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    public static final int CROSS_ADJACENT_MANHATTAN_DISTANCE = 1;
    public static final int DIAGONAL_ADJACENT_MANHATTAN_DISTANCE = 2;

    private static final Map<String, Position> positionCache = new HashMap<>();

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        final String fileRankCode = file.getCode() + rank.getCode();
        positionCache.putIfAbsent(fileRankCode, new Position(file, rank));
        return positionCache.get(fileRankCode);
    }

    public boolean isInCrossPosition(final Position otherPosition) {
        return isInVerticalPosition(otherPosition) || isInHorizontalPosition(otherPosition);
    }

    public boolean isInHorizontalPosition(final Position otherPosition) {
        return this.rank == otherPosition.rank && !this.equals(otherPosition);
    }

    public boolean isInVerticalPosition(final Position otherPosition) {
        return this.file == otherPosition.file && !this.equals(otherPosition);
    }

    public boolean isInDiagonalPosition(final Position otherPosition) {
        return (calculateFileDistance(otherPosition) == calculateRankDistance(otherPosition)) && !this.equals(otherPosition);
    }

    private int calculateFileDistance(final Position otherPosition) {
        return this.file.calculateDistance(otherPosition.file);
    }

    private int calculateRankDistance(final Position otherPosition) {
        return this.rank.calculateDistance(otherPosition.rank);
    }

    public int calculateManhattanDistance(final Position otherPosition) {
        return calculateFileDistance(otherPosition) + calculateRankDistance(otherPosition);
    }

    public boolean isUpperRankThan(final Position otherPosition) {
        return this.rank.isUpperThan(otherPosition.rank);
    }

    public boolean isLowerRankThan(final Position otherPosition) {
        return this.rank.isLowerThan(otherPosition.rank);
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
