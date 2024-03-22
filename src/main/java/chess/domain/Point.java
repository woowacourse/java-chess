package chess.domain;

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
        this(new File(file), Rank.from(rank));
    }

    public Point(String point) {
        this(new File(point.charAt(0)), Rank.from(point.charAt(1) - '0'));
    }

    public Direction findRoute(Point destination) {
        int fileDistance = destination.file.distance(this.file);
        int rankDistance = rank.calculateDistanceFrom(destination.rank);
        int unitFile = fileDistance == 0 ? 0 : fileDistance / Math.abs(fileDistance);
        int unitRank = rankDistance == 0 ? 0 : rankDistance / Math.abs(rankDistance);

        if (fileDistance == 0 || rankDistance == 0) {
            return Direction.of(unitFile, unitRank);
        }
        if (isDiagonal(destination)) {
            return Direction.of(unitFile, unitRank);
        }
        return Direction.of(fileDistance, rankDistance);
    }

    public boolean isDiagonal(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.calculateDistanceFrom(point.rank);
        if (this.equals(point) || rankDistance == 0) {
            return false;
        }
        return Math.abs((double) fileDistance / rankDistance) == 1;
    }

    public boolean isStraight(Point point) {
        if (this.equals(point)) {
            return false;
        }
        return this.file.equals(point.file) || this.rank.equals(point.rank);
    }

    public boolean isAround(Point point) {
        if (this.equals(point)) {
            return false;
        }
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.calculateDistanceFrom(point.rank);
        int distance = getDistance(fileDistance, rankDistance);
        if (fileDistance != 0 && rankDistance != 0) {
            return distance == 2;
        }
        return distance == 1;
    }

    private int getDistance(int fileDistance, int rankDistance) {
        return Math.abs(fileDistance) + Math.abs(rankDistance);
    }

    public int multiplyAxis(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.calculateDistanceFrom(point.rank);
        return fileDistance * rankDistance;
    }

    public boolean isInitialPointOfPawn() {
        return rank.isFirstPositionOfPawn();
    }

    public Point add(int directionOfFile, int distanceToMove) {
        File addedFile = file.add(directionOfFile);
        Rank addedRank = rank.move(distanceToMove);

        return new Point(addedFile, addedRank);
    }

    public boolean addable(int addFile, int distanceToMove) {
        return file.addable(addFile) && rank.canMove(distanceToMove);
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
