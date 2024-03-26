package chess.domain;

import chess.domain.piece.Direction;
import java.util.LinkedHashMap;
import java.util.Map;

public class Position {

    private static final Map<String, Position> CACHE = new LinkedHashMap<>();
    private static final char START_VALUE_OF_FILE = 'a';
    private static final char END_VALUE_OF_FILE = 'h';
    private static final int START_VALUE_OF_RANK = 1;
    private static final int END_VALUE_OF_RANK = 8;

    private final char file;
    private final int rank;

    static {
        for (char i = START_VALUE_OF_FILE; i <= END_VALUE_OF_FILE; i++) {
            for (int j = START_VALUE_OF_RANK; j <= END_VALUE_OF_RANK; j++) {
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

    public static Position from(final String rawPosition) {
        char file = rawPosition.charAt(0);
        int rank = Character.getNumericValue(rawPosition.charAt(1));

        return Position.of(file, rank);
    }

    private static String toKey(final char file, final int rank) {
        return String.valueOf(file) + rank;
    }

    public Position moveTowardDirection(final Direction direction) {
        char file = (char) direction.calculateNextFile(this.file);
        int rank = direction.calculateNextRank(this.rank);

        if (file < START_VALUE_OF_FILE || file > END_VALUE_OF_FILE ||
                rank < START_VALUE_OF_RANK || rank > END_VALUE_OF_RANK) {
            throw new IllegalArgumentException("[ERROR] 체스판의 범위를 벗어난 위치로 이동할 수 없습니다");
        }

        return Position.of(file, rank);
    }

    public Direction calculateDirection(final Position target) {
        int fileDifference = target.file - this.file;
        int rankDifference = target.rank - this.rank;

        return Direction.find(fileDifference, rankDifference);
    }

    public boolean isSameRank(final int targetRank) {
        return this.rank == targetRank;
    }
}
