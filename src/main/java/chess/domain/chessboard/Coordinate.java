package chess.domain.chessboard;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class Coordinate {
    private static final String REGEX = "^[a-h][1-8]$";
    private static final int FILE_PARSE_INDEX = 0;
    private static final int RANK_PARSE_INDEX = 1;

    private static final Map<String, Coordinate> cache = new HashMap<>();

    private final int fileIndex;
    private final int rankIndex;

    private Coordinate(final String alphanumeric) {
        validateAlphanumeric(alphanumeric);
        this.fileIndex = parsingFileIndex(alphanumeric);
        this.rankIndex = parsingRankIndex(alphanumeric);
    }

    public static Coordinate of(final String alphanumeric) {
        if(!cache.containsKey(alphanumeric)){
            cache.put(alphanumeric, new Coordinate(alphanumeric));
        }

        return cache.get(alphanumeric);
    }

    private void validateAlphanumeric(final String alphanumeric) {
        if (!Pattern.matches(REGEX, alphanumeric)) {
            throw new IllegalArgumentException("잘못된 좌표값입니다." + alphanumeric);
        }
    }

    private int parsingFileIndex(final String alphanumeric) {
        return alphanumeric.charAt(FILE_PARSE_INDEX) - 97;
    }

    private int parsingRankIndex(final String alphanumeric) {
        return alphanumeric.charAt(RANK_PARSE_INDEX) - 49;
    }
}
