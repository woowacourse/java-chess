package chess.domain.position;

import chess.domain.route.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private File file;
    private Rank rank;

    private static final Map<String, Position> cache = new HashMap<>();

    static {
        for (File file : File.values()) {
            createCacheByRank(cache, file);
        }
    }

    private static void createCacheByRank(Map<String, Position> cache, File file) {
        for (Rank rank : Rank.values()) {
            cache.put(key(file, rank), new Position(file, rank));
        }
    }

    private static String key(File file, Rank rank) {
        return file.toString() + rank.toString();
    }

    public static Position of(String key) {
        Position position = cache.get(key);

        if (position == null) {
            throw new IllegalArgumentException("존재하지 않는 위치입니다.");
        }

        return position;
    }

    public static Position of(File file, Rank rank) {
        return of(file.toString() + rank.toString());
    }

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean isAt(Rank rank) {
        return this.rank == rank;
    }

    public boolean isAt(File file) {
        return this.file == file;
    }

    public Position moveTo(Direction direction) {
        File movedFile = file.plus(direction.getXDegree());
        Rank movedRank = rank.plus(direction.getYDegree());
        return new Position(movedFile, movedRank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file &&
                rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }
}
