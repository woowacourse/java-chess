package chess.model.position;

import chess.model.direction.route.Route;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private final static Map<String, Position> cache = new HashMap<>();

    private final Rank rank;
    private final File file;

    static {
        for (Rank rank : Rank.values()) {
            createCacheFrom(rank);
        }
    }

    private Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(final Rank rank, final File file) {
        String key = createKey(rank, file);
        return from(key);
    }

    public static Position from(final String key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 값이 입력되었습니다.");
    }

    private static void createCacheFrom(Rank rank) {
        for (File file : File.values()) {
            String key = file.nameOfFile() + rank.nameOfRank();
            cache.put(key, new Position(rank, file));
        }
    }

    private static String createKey(Rank rank, File file) {
        return file.nameOfFile() + rank.nameOfRank();
    }

    public int subtractRankFrom(final Position otherPosition) {
        return rank.subtractFrom(otherPosition.rank);
    }

    public int subtractFileFrom(final Position otherPosition) {
        return file.subtractFrom(otherPosition.file);
    }

    public Position createPositionTo(final Route route) {
        return route.createPositionFrom(rank, file);
    }

    public boolean isTwoRank() {
        return rank == Rank.TWO;
    }

    public boolean isSevenRank() {
        return rank == Rank.SEVEN;
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
