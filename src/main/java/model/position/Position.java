package model.position;

import model.direction.Direction;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Position {
    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    private static final Set<Position> cache = new HashSet<>();

    static {
        for (File file : File.values()) {
            initRank(file);
        }
    }

    private static void initRank(File file) {
        for (Rank rank : Rank.values()) {
            cache.add(new Position(file, rank));
        }
    }

    public boolean isAvailablePosition(Direction direction) {
        return file.canMoveTo(direction) && rank.canMoveTo(direction);
    }

    public Position getNextPosition(Direction direction) {
        return Position.of(file.moving(direction), rank.moving(direction));
    }

    public static Position of(File file, Rank rank) {
        return cache.stream()
                    .filter(position -> position.file == file && position.rank == rank)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Position 입니다."));
    }

    public File file() {
        return file;
    }

    public Rank rank() {
        return rank;
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
        return Objects.hash(file, rank);
    }
}
