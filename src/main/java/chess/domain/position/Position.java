package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    private static final Map<String, Position> CACHE;

    static {
        CACHE = new HashMap<>();
    }

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        final String key = file.name() + rank.name();
        CACHE.putIfAbsent(key, new Position(file, rank));
        return CACHE.get(key);
    }

    public static Position from(String rawPosition) {
        File file = File.valueOf(rawPosition.substring(0, 1).toUpperCase());
        Rank rank = Rank.from(Integer.parseInt(rawPosition.substring(1)));
        CACHE.putIfAbsent(rawPosition.toUpperCase(), new Position(file, rank));
        return CACHE.get(rawPosition.toUpperCase());
    }

    public int calculateFileDistance(final Position source) {
        return rank.calculateDistance(source.rank);
    }

    public int calculateRankDistance(final Position source) {
        return file.calculateDistance(source.file);
    }

    public Position move(final int rankDirection, final int fileDirection) {
        return Position.of(file.plus(rankDirection), rank.plus(fileDirection));
    }

    public boolean isSameRank(final Rank rank) {
        return this.rank == rank;
    }

    public boolean isSameFile(final File file) {
        return this.file == file;
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
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
