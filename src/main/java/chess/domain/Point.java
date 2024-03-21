package chess.domain;

import chess.domain.piece.Direction;
import java.util.Objects;
import java.util.Optional;

public class Point {

    private final File file;
    private final Rank rank;

    public Point(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Point(char file, int rank) {
        this(new File(file), new Rank(rank));
    }

    public Point(String point) {
        this(new File(point.charAt(0)), new Rank(point.charAt(1) - '0'));
    }

    public boolean isDiagonal(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
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
        int rankDistance = this.rank.distance(point.rank);
        int distance = getDistance(fileDistance, rankDistance);
        if (fileDistance != 0 || rankDistance != 0) {
            return distance == 2;
        }
        return distance == 1;
    }

    private int getDistance(int fileDistance, int rankDistance) {
        return Math.abs(fileDistance) + Math.abs(rankDistance);
    }

    public int multiplyAxis(Point point) {
        int fileDistance = this.file.distance(point.file);
        int rankDistance = this.rank.distance(point.rank);
        return fileDistance * rankDistance;
    }

    // TODO: 이름 변경
    public boolean isInitialPointOfPawn() {
        return rank.isFirstRank();
    }

    public Optional<Point> add(int directionOfFile, int directionOfRank) {
        Optional<File> fileOpt = file.add(directionOfFile);
        Optional<Rank> rankOpt = rank.add(directionOfRank);

        if (fileOpt.isEmpty() || rankOpt.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Point(fileOpt.get(), rankOpt.get()));
    }

    public Direction findRoute(Point point) {
        int fileDistance = point.file.distance(this.file);
        int rankDistance = point.rank.distance(this.rank);
        int unitFile = fileDistance == 0 ? 0 : fileDistance / Math.abs(fileDistance);
        int unitRank = rankDistance == 0 ? 0 : rankDistance / Math.abs(rankDistance);

        if (fileDistance == 0 || rankDistance == 0) {
            return Direction.of(unitFile, unitRank);
        }
        if (isDiagonal(point)) {
            return Direction.of(unitFile, unitRank);
        }
        return Direction.of(fileDistance, rankDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(file, point.file) && Objects.equals(rank, point.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
