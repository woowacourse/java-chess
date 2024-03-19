package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Square {

    private static final Map<String, Square> CACHE = new LinkedHashMap<>();

    private final char rank;
    private final int file;

    static {
        for (char i = 'a'; i <= 'h'; i++) {
            for (int j = 1; j <= 8; j++) {
                CACHE.put(toKey(i, j), new Square(i, j));
            }
        }
    }

    private Square(char rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public static Square of(char rank, int file) {
        return CACHE.get(toKey(rank, file));
    }

    private static String toKey(char rank, int file) {
        return String.valueOf(rank) + file;
    }
}
