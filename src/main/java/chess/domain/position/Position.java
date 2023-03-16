package chess.domain.position;

import chess.domain.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.hash;

public class Position {

    private static final Map<Integer, Position> cache;
    private final File file;
    private final Rank rank;

    static {
        cache = new HashMap<>();
    }

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

    public boolean isStraightEqual(Position other) {
        return this.file == other.file || this.rank == other.rank;
    }

    public boolean isDiagonalEqual(Position other) {
        return this.file.distance(other.file) == this.rank.distance(other.rank);
    }

    public boolean isNear(Position other){
        if(getMaxDistance(other) <= 1){
            return true;
        }
        return false;
    }

    public boolean isKnightPosition(Position other){
        if (file.distance(other.file) == 2 && rank.distance(other.rank) == 1) {
            return true;
        }
        if (rank.distance(other.rank) == 2 && file.distance(other.file) == 1) {
            return true;
        }
        return false;
    }

    public List<Position> getRoute(Position other) {
        Direction rankDirection = rank.getDirection(other.rank);
        Direction fileDirection = file.getDirection(other.file);
        int distance = getMaxDistance(other);
        List<Position> route = getRouteToDirection(rankDirection, fileDirection, distance);
        return route;
    }

    private int getMaxDistance(Position other) {
        int fileDistance = file.distance(other.file);
        int rankDistance = rank.distance(other.rank);
        return Math.max(fileDistance, rankDistance);
    }

    private List<Position> getRouteToDirection(Direction rankDirection, Direction fileDirection, int distance) {
        List<Position> route = new ArrayList<>();
        Rank currentRank = rank;
        File currentFile = file;
        for (int i = 0; i < distance - 1; i++) {
            Rank newRank = currentRank.moveToDirection(rankDirection);
            File newFile = currentFile.moveToDirection(fileDirection);
            route.add(Position.of(newFile, newRank));
            currentRank = newRank;
            currentFile = newFile;
        }
        return route;
    }

    public Position moveRank(Direction direction){
        return Position.of(file, direction.move(this.rank, 1));
    }

    public Position moveRank(Direction direction, int distance){
        return Position.of(file, direction.move(this.rank, distance));
    }

    public Position move(Direction fileDirection, Direction rankDirection){
        return Position.of(fileDirection.move(this.file), rankDirection.move(this.rank, 1));
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

    public List<Integer> getCoordinate() {
        List<Integer> coordinate = new ArrayList<>();

        coordinate.add(rank.getRankIndex());
        coordinate.add(file.getFileIndex());
        return coordinate;
    }

    public boolean isSameRank(Rank other) {
        return rank.equals(other);
    }
}
