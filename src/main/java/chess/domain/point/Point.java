package chess.domain.point;

import chess.domain.piece.Direction;

import java.util.Objects;

public class Point {
    private final File file;
    private final Rank rank;

    public Point(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Point(char file, int rank) {
        this(File.from(file), Rank.from(rank));
    }

    public Direction findRoute(Point destination) {
        int fileDistance = calculateFileDistance(destination);
        int rankDistance = calculateRankDistance(destination);
        int unitFile = fileDistance == 0 ? 0 : fileDistance / Math.abs(fileDistance);
        int unitRank = rankDistance == 0 ? 0 : rankDistance / Math.abs(rankDistance);

        if (fileDistance == 0 || rankDistance == 0) {
            return Direction.of(unitFile, unitRank);
        }
        if (isDiagonalWithSlopeOfOne(destination)) {
            return Direction.of(unitFile, unitRank);
        }
        return Direction.of(fileDistance, rankDistance);
    }

    public boolean isDiagonalWithSlopeOfOne(Point destination) {
        if (this.equals(destination)) {
            return false;
        }
        return Math.abs(calculateSlope(destination)) == 1;
    }

    private double calculateSlope(final Point destination) {
        int fileDistance = calculateFileDistance(destination);
        int rankDistance = calculateRankDistance(destination);

        if (rankDistance == 0) {
            return Double.MAX_VALUE;
        }
        return (double) fileDistance / rankDistance;
    }

    public boolean isStraight(Point destination) {
        if (this.equals(destination)) {
            return false;
        }
        return this.file == destination.file || this.rank == destination.rank;
    }

    public boolean isAround(Point destination) {
        if (this.equals(destination)) {
            return false;
        }
        int fileDistance = calculateFileDistance(destination);
        int rankDistance = calculateRankDistance(destination);
        int totalDistance = Math.abs(fileDistance) + Math.abs(rankDistance);
        if (fileDistance != 0 && rankDistance != 0) {
            return totalDistance == 2;
        }
        return totalDistance == 1;
    }

    public int multiplyAxis(Point destination) {
        int fileDistance = calculateFileDistance(destination);
        int rankDistance = calculateRankDistance(destination);
        return fileDistance * rankDistance;
    }

    private int calculateFileDistance(final Point destination) {
        return file.calculateDistanceFrom(destination.file);
    }

    private int calculateRankDistance(final Point destination) {
        return rank.calculateDistanceFrom(destination.rank);
    }

    public Point add(int directionOfFile, int distanceToMove) {
        File addedFile = file.move(directionOfFile);
        Rank addedRank = rank.move(distanceToMove);

        return new Point(addedFile, addedRank);
    }

    public boolean addable(int addFile, int distanceToMove) {
        return file.canMove(addFile) && rank.canMove(distanceToMove);
    }

    public boolean isSecondRank() {
        return this.rank == Rank.TWO;
    }

    public boolean isSeventhRank() {
        return this.rank == Rank.SEVEN;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point point = (Point) o;
        return Objects.equals(file, point.file) && rank == point.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
