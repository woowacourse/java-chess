package chess.domain.chessboard;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class Coordinate {
    private static final String REGEX = "^[a-h][1-8]$";
    private static final int FILE_PARSE_INDEX = 0;
    private static final int RANK_PARSE_INDEX = 1;

    private static final Map<String, Coordinate> cache = new HashMap<>();

    private final char fileIndex;
    private final char rankIndex;

    private Coordinate(final String alphanumeric) {
        validateAlphanumeric(alphanumeric);
        this.fileIndex = parsingFileIndex(alphanumeric);
        this.rankIndex = parsingRankIndex(alphanumeric);
    }

    public static Coordinate of(final String alphanumeric) {
        if (!cache.containsKey(alphanumeric)) {
            cache.put(alphanumeric, new Coordinate(alphanumeric));
        }

        return cache.get(alphanumeric);
    }

    private void validateAlphanumeric(final String alphanumeric) {
        if (!Pattern.matches(REGEX, alphanumeric)) {
            throw new IllegalArgumentException("잘못된 좌표값입니다." + alphanumeric);
        }
    }

    private char parsingFileIndex(final String alphanumeric) {
        return alphanumeric.charAt(FILE_PARSE_INDEX);
    }

    private char parsingRankIndex(final String alphanumeric) {
        return alphanumeric.charAt(RANK_PARSE_INDEX);
    }

    public boolean isSameRank(final Coordinate other){
        return this.rankIndex == other.rankIndex;
    }

    public boolean isSameFile(final Coordinate other){
        return this.fileIndex == other.fileIndex;
    }

    public Coordinate verticalMove(final int step) {
        return Coordinate.of(fileIndex + addIndex(rankIndex, step));
    }


    public Coordinate horizontalMove(final int step) {
        return Coordinate.of(addIndex(fileIndex, step) + rankIndex);
    }

    public Coordinate positiveDiagonalMove(final int step) {
        return Coordinate.of(addIndex(fileIndex, step) + addIndex(rankIndex, step));
    }

    public Coordinate negativeDiagonalMove(final int step) {
        return Coordinate.of(subtractIndex(fileIndex, step) + addIndex(rankIndex, step));
    }

    private String addIndex(final char index, final int step) {
        return Character.toString(index + step);
    }

    private String subtractIndex(final char index, final int step) {
        return Character.toString(index - step);
    }
}
