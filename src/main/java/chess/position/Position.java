package chess.position;

import java.util.*;
import java.util.stream.Collectors;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isVerticalWay(Position other) {
        return this.file == other.file;
    }

    public boolean isHorizontalWay(Position other) {
        return this.rank == other.rank;
    }

    public boolean isDiagonalWay(Position other) {
        return getVerticalDistance(other) == getHorizontalDistance(other);
    }

    public boolean isAdjacent(Position other) {
        return (getVerticalDistance(other) | getHorizontalDistance(other)) == 1;
    }

    public boolean isUpward(Position other) {
        return rank.isUpward(other.rank);
    }

    public boolean isDownward(Position other) {
        return rank.isDownward(other.rank);
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public int getVerticalDistance(Position other) {
        return rank.getDistance(other.rank);
    }

    public int getHorizontalDistance(Position other) {
        return file.getDistance(other.file);
    }

    public Collection<Position> getPath(Position to) {
        if (isVerticalWay(to)) {
            return getVerticalPath(to);
        }
        if (isHorizontalWay(to)) {
            return getHorizontalPath(to);
        }
        if (isDiagonalWay(to)) {
            return getDiagonalPath(to);
        }
        return List.of();
    }

    private List<Position> getVerticalPath(Position to) {
        return rank.getPath(to.rank).stream()
            .map(rank -> new Position(file, rank))
            .collect(Collectors.toList());
    }

    private List<Position> getHorizontalPath(Position to) {
        return file.getPath(to.file).stream()
            .map(file -> new Position(file, rank))
            .collect(Collectors.toList());
    }

    private Collection<Position> getDiagonalPath(Position to) {
        List<Rank> ranks = rank.getPath(to.rank);
        List<File> files = file.getPath(to.file);

        Collection<Position> result = new ArrayList<>();
        for (int i = 0; i < ranks.size(); i++) {
            result.add(new Position(files.get(i), ranks.get(i)));
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
            "row=" + file +
            ", col=" + rank +
            '}';
    }
}
