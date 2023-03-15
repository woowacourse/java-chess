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

    public List<Position> getRoute(Position other) {
        Direction rankDirection = rank.getDirection(other.rank);
        Direction fileDirection = file.getDirection(other.file);
        int distance = getDistance(other);
        List<Position> route = getRouteToDirection(rankDirection, fileDirection, distance);
        return route;
    }

    private int getDistance(Position other) {
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
}
