package chess.domain.position;

import java.util.HashMap;
import java.util.Map;

public class Position {
    private File file;
    private Rank rank;

    private static final Map<String, Position> cache = new HashMap<>();

    static {
        for (File file : File.values()) {
            createCacheByRank(cache, file);
        }
    }

    private static String key(File file, Rank rank) {
        return file.toString() + rank.toString();
    }

    private static void createCacheByRank(Map<String, Position> cache, File file) {
        for (Rank rank : Rank.values()) {
            cache.put(key(file, rank), new Position(file, rank));
        }
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

    public Position increaseFile(int number) {
        return Position.of(this.file.plus(number), this.rank);
    }

    public Position increaseRank(int number) {
        return Position.of(this.file, this.rank.plus(number));
    }

    public Position increaseDiagonal(int number) {
        return Position.of(this.file.plus(number), this.rank.plus(number));
    }

    public boolean isAt(Rank rank) {
        return this.rank == rank;
    }

    public Position reverse() {
        return new Position(file.reverse(), rank.reverse());
    }
}
