package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    private static final Map<String, Position> CACHE;

    static {
        CACHE = new HashMap<>();
    }

    private Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(Rank rank, File file) {
        final String key = rank.name() + file.name();
        CACHE.putIfAbsent(key, new Position(rank, file));
        return CACHE.get(key);
    }

    public static Position from(String rawPosition) {
        Rank rank = Rank.from(rawPosition.substring(0, 1));
        File file = File.from(Integer.parseInt(rawPosition.substring(1)));
        CACHE.putIfAbsent(rawPosition.toUpperCase(), new Position(rank, file));
        return CACHE.get(rawPosition.toUpperCase());
    }

    public int calculateFileDistance(final Position source) {
        return file.calculateDistance(source.file);
    }

    public int calculateRankDistance(final Position source) {
        return rank.calculateDistance(source.rank);
    }

    public Position move(final int rankDirection, final int fileDirection) {
        return Position.of(rank.plus(rankDirection), file.plus(fileDirection));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    public boolean isSameRank(final Rank rank) {
        return this.rank == rank;
    }

    public boolean isSameFile(final File file) {
        return this.file == file;
    }
}
