package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Position {

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();

    private final char rank;
    private final int file;

    static {
        for (char i = 'a'; i <= 'h'; i++) {
            for (int j = 1; j <= 8; j++) {
                CACHE.put(toKey(i, j), new Position(i, j));
            }
        }
    }

    private Position(final char rank, final int file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(final char rank, final int file) {
        return CACHE.get(toKey(rank, file));
    }

    private static String toKey(final char rank, final int file) {
        return String.valueOf(rank) + file;
    }
}
