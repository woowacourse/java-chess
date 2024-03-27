package chess.domain.point;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Point {
    private static final Map<String, Point> CACHED_POINT = Arrays.stream(File.values())
            .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> new Point(file, rank)))
            .collect(Collectors.toMap(it -> toKey(it.file, it.rank), Function.identity()));

    private final File file;
    private final Rank rank;

    private Point(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Point of(final char file, final int rank) {
        final String key = toString(file, rank);

        if (CACHED_POINT.containsKey(key)) {
            return CACHED_POINT.get(key);
        }
        throw new IllegalArgumentException("일치하는 값을 찾을 수 없습니다.");
    }

    private static String toKey(final File file, final Rank rank) {
        final char filePosition = file.getPosition();
        final int rankPosition = rank.getPosition();

        return toString(filePosition, rankPosition);
    }

    private static String toString(final char file, final int rank) {
        return String.valueOf(file) + rank;
    }

    public Direction findRoute(final Point destination) {
        final int fileDistance = calculateFileDistance(destination);
        final int rankDistance = calculateRankDistance(destination);
        final int unitFile = fileDistance == 0 ? 0 : fileDistance / Math.abs(fileDistance);
        final int unitRank = rankDistance == 0 ? 0 : rankDistance / Math.abs(rankDistance);

        if (fileDistance == 0 || rankDistance == 0) {
            return Direction.of(unitFile, unitRank);
        }
        if (isDiagonalWithSlopeOfOne(destination)) {
            return Direction.of(unitFile, unitRank);
        }
        return Direction.of(fileDistance, rankDistance);
    }

    public boolean isDiagonalWithSlopeOfOne(final Point destination) {
        if (this.equals(destination)) {
            return false;
        }
        return Math.abs(calculateSlope(destination)) == 1;
    }

    private double calculateSlope(final Point destination) {
        final int fileDistance = calculateFileDistance(destination);
        final int rankDistance = calculateRankDistance(destination);

        if (rankDistance == 0) {
            return Double.MAX_VALUE;
        }
        return (double) fileDistance / rankDistance;
    }

    public boolean isStraight(final Point destination) {
        if (this.equals(destination)) {
            return false;
        }
        return this.file == destination.file || this.rank == destination.rank;
    }

    public boolean isAround(final Point destination) {
        if (this.equals(destination)) {
            return false;
        }
        final int fileDistance = calculateFileDistance(destination);
        final int rankDistance = calculateRankDistance(destination);
        final int totalDistance = Math.abs(fileDistance) + Math.abs(rankDistance);
        if (fileDistance != 0 && rankDistance != 0) {
            return totalDistance == 2;
        }
        return totalDistance == 1;
    }

    public int multiplyAxis(final Point destination) {
        final int fileDistance = calculateFileDistance(destination);
        final int rankDistance = calculateRankDistance(destination);
        return fileDistance * rankDistance;
    }

    private int calculateFileDistance(final Point destination) {
        return file.calculateDistanceFrom(destination.file);
    }

    private int calculateRankDistance(final Point destination) {
        return rank.calculateDistanceFrom(destination.rank);
    }

    public Point add(final int directionOfFile, final int distanceToMove) {
        final char addedFilePosition = file.addPosition(directionOfFile);
        final int addedRankPosition = rank.addPosition(distanceToMove);

        return Point.of(addedFilePosition, addedRankPosition);
    }

    public boolean addable(final int addFile, final int distanceToMove) {
        return file.canMove(addFile) && rank.canMove(distanceToMove);
    }

    public boolean isSecondRank() {
        return this.rank == Rank.TWO;
    }

    public boolean isSeventhRank() {
        return this.rank == Rank.SEVEN;
    }
}
