package chess.domain.position;

import java.util.*;
import java.util.stream.Collectors;

public class Position {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final Map<Integer, Position> cache = new HashMap<>();

    private final Rank rank;
    private final File file;

    private Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(final Rank rank, final File file) {
        return cache.computeIfAbsent(toKey(rank, file), key -> new Position(rank, file));
    }

    public static Position of(String rankAndFile) {
        rankAndFile = rankAndFile.strip();
        validateLength(rankAndFile);

        final String[] split = rankAndFile.split("");
        final String file = split[FILE_INDEX];
        final String rank = split[RANK_INDEX];
        return Position.of(Rank.from(rank), File.from(file));
    }

    private static void validateLength(final String rankAndFile) {
        if (rankAndFile.length() != 2) {
            throw new IllegalArgumentException("존재하지 않는 위치입니다");
        }
    }

    private static Integer toKey(final Rank rank, final File file) {
        return Objects.hash(rank, file);
    }

    public boolean isAtLowRank(final Position other) {
        return rank.isSmallerThan(other.rank);
    }

    public List<Position> positionsOfPath(Position to) {
        if (isLine(to)) {
            return positionsOfLine(to);
        }
        if (isDiagonal(to)) {
            return positionsOfDiagonal(to);
        }
        return Collections.emptyList();
    }

    private boolean isLine(final Position other) {
        validateNotSameSquare(other);

        final int verticalDistance = calculateVerticalDistance(other);
        final int horizontalDistance = calculateHorizontalDistance(other);

        return verticalDistance == 0 || horizontalDistance == 0;
    }

    private boolean isDiagonal(final Position other) {
        validateNotSameSquare(other);

        final int verticalDistance = calculateVerticalDistance(other);
        final int horizontalDistance = calculateHorizontalDistance(other);

        return verticalDistance == horizontalDistance;
    }

    private void validateNotSameSquare(final Position other) {
        if (this == other) {
            throw new IllegalArgumentException("같은 위치의 position입니다");
        }
    }

    private List<Position> positionsOfLine(final Position otherPosition) {
        if (isAtRank(otherPosition.rank)) {
            return positionsOfRank(otherPosition);
        }
        return positionsOfFile(otherPosition);
    }

    private List<Position> positionsOfRank(final Position other) {
        return File.filesBetween(this.file, other.file)
                   .stream()
                   .map(foundFile -> Position.of(rank, foundFile))
                   .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> positionsOfFile(final Position otherPosition) {
        return Rank.ranksBetween(this.rank, otherPosition.rank)
                   .stream()
                   .map(foundRank -> Position.of(foundRank, file))
                   .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> positionsOfDiagonal(final Position otherPosition) {
        final List<Rank> ranks = Rank.ranksBetween(this.rank, otherPosition.rank);
        final List<File> files = File.filesBetween(this.file, otherPosition.file);
        final List<Position> positions = new ArrayList<>();

        for (int i = 0; i < ranks.size(); i++) {
            final Position nextPosition = Position.of(ranks.get(i), files.get(i));
            positions.add(nextPosition);
        }

        return positions;
    }

    public int calculateVerticalDistance(final Position other) {
        return rank.distanceTo(other.rank);
    }

    public int calculateHorizontalDistance(final Position other) {
        return file.distanceTo(other.file);
    }

    public boolean isAtRank(final Rank rank) {
        return this.rank == rank;
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }
}
