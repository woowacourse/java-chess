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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    public boolean isDiagonalEqual(Position other) {
        return this.file.distance(other.file) == this.rank.distance(other.rank);
    }

    @Override
    public int hashCode() {
        return hash(file, rank);
    }

    public List<Position> getDiagonalRoute(Position other) {
        Direction rankDirection = rank.getDirection(other.rank);
        Direction fileDirection = file.getDirection(other.file);
        int distance = file.distance(other.file);
        List<Position> route = getRoutePositions(rankDirection, fileDirection, distance);
        return route;
    }

    private List<Position> getRoutePositions(Direction rankDirection, Direction fileDirection, int distance) {
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
}
