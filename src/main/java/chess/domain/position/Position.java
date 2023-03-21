package chess.domain.position;

import chess.domain.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.hash;

public class Position {

    private static final Map<Integer, Position> cache = new HashMap<>();
    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;
    public static final int TWO_SQUARE = 2;
    public static final int ONE_SQUARE = 1;

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        int hash = hash(file, rank);
        if (cache.get(hash) == null) {
            cache.put(hash, new Position(file, rank));
        }
        return cache.get(hash);
    }

    public static Position of(String positionCommand) {
        char fileCommand = positionCommand.charAt(FILE_INDEX);
        char rankCommand = positionCommand.charAt(RANK_INDEX);
        return of(File.of(fileCommand), Rank.of(rankCommand));
    }

    public boolean isNear(Position other) {
        if (getMaxDistance(other) <= ONE_SQUARE) {
            return true;
        }
        return false;
    }

    public boolean isStraightEqual(Position other) {
        return this.file == other.file || this.rank == other.rank;
    }

    public boolean isDiagonalEqual(Position other) {
        return this.file.distance(other.file) == this.rank.distance(other.rank);
    }

    public boolean isKnightPosition(Position other) {
        if (file.distance(other.file) == TWO_SQUARE && rank.distance(other.rank) == ONE_SQUARE) {
            return true;
        }
        if (rank.distance(other.rank) == TWO_SQUARE && file.distance(other.file) == ONE_SQUARE) {
            return true;
        }
        return false;
    }

    public List<Position> getRoute(Position other) {
        int distance = getMaxDistance(other);
        return getRouteToDirection(other.rank, other.file, distance);
    }

    private int getMaxDistance(Position other) {
        int fileDistance = file.distance(other.file);
        int rankDistance = rank.distance(other.rank);
        return Math.max(fileDistance, rankDistance);
    }

    private List<Position> getRouteToDirection(Rank nextRank, File nextFile, int distance) {
        List<Position> route = new ArrayList<>();
        Rank movingRank = rank;
        File movingFile = file;
        for (int i = 0; i < distance - 1; i++) {
            movingRank = movingRank.moveOnceToOther(nextRank);
            movingFile = movingFile.moveOnceToOther(nextFile);

            route.add(Position.of(movingFile, movingRank));
        }
        return route;
    }

    public Position move(Direction fileDirection, Direction rankDirection) {
        return Position.of(fileDirection.move(this.file), rankDirection.move(this.rank, 1));
    }

    public List<Integer> getCoordinate() {
        List<Integer> coordinate = new ArrayList<>();

        coordinate.add(rank.getRankIndex());
        coordinate.add(file.getFileIndex());
        return coordinate;
    }

    public boolean isSameRank(Rank other) {
        return rank.equals(other);
    }

    public boolean isSameRank(Position other) {
        return rank.equals(other.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
