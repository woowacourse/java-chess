package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Position {

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();

    private final char file;
    private final int rank;

    static {
        for (char i = 'a'; i <= 'h'; i++) {
            for (int j = 1; j <= 8; j++) {
                CACHE.put(toKey(i, j), new Position(i, j));
            }
        }
    }

    private Position(final char file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final char file, final int rank) {
        return CACHE.get(toKey(file, rank));
    }

    private static String toKey(final char file, final int rank) {
        return String.valueOf(file) + rank;
    }


    public double calculateGradient(final Position target) {
        double dx = this.file - target.file;
        double dy = this.rank - target.rank;

        // TODO: 개선 필요
        if(dy == 0) {
            return 0;
        }

        return dy / dx;
    }

    public double calculateDistance(final Position target) {
        return Math.sqrt(Math.pow(this.file - target.file, 2) + Math.pow(this.rank - target.rank, 2));
    }
}
