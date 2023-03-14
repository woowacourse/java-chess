package chess.domain;

import java.util.HashMap;
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

    @Override
    public int hashCode() {
        return hash(file, rank);
    }
}
