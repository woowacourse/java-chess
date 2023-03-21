package chess.domain.chessboard;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class SquareCoordinate {
    private static final String ALPHANUMERIC_REGEX = "^[a-h][1-8]$";
    private static final int FILE_PARSE_INDEX = 0;
    private static final int RANK_PARSE_INDEX = 1;

    private static final Map<String, SquareCoordinate> cache = new HashMap<>();

    private final char fileIndex;
    private final char rankIndex;

    private SquareCoordinate(final String alphanumeric) {
        validateAlphanumeric(alphanumeric);
        this.fileIndex = parsingFileIndex(alphanumeric);
        this.rankIndex = parsingRankIndex(alphanumeric);
    }

    public static SquareCoordinate of(final String alphanumeric) {
        return cache.computeIfAbsent(alphanumeric, key -> new SquareCoordinate(alphanumeric));
    }

    private void validateAlphanumeric(final String alphanumeric) {
        if (!Pattern.matches(ALPHANUMERIC_REGEX, alphanumeric)) {
            throw new IllegalArgumentException("잘못된 좌표값입니다." + alphanumeric);
        }
    }

    private char parsingFileIndex(final String alphanumeric) {
        return alphanumeric.charAt(FILE_PARSE_INDEX);
    }

    private char parsingRankIndex(final String alphanumeric) {
        return alphanumeric.charAt(RANK_PARSE_INDEX);
    }

    public boolean isSameRank(final SquareCoordinate other) {
        return this.rankIndex == other.rankIndex;
    }

    public boolean isSameFile(final SquareCoordinate other) {
        return this.fileIndex == other.fileIndex;
    }

    public boolean isPositiveDiagonal(final SquareCoordinate other) {
        final int rankDistance = other.rankIndex - this.rankIndex;
        final int fileDistance = other.fileIndex - this.fileIndex;

        return rankDistance == fileDistance;
    }

    public boolean isNegativeDiagonal(final SquareCoordinate other) {
        final int rankDistance = other.rankIndex - this.rankIndex;
        final int fileDistance = other.fileIndex - this.fileIndex;

        return rankDistance == (-1 * fileDistance);
    }

    private String addIndex(final char index, final int step) {
        return Character.toString(index + step);
    }

    private String subtractIndex(final char index, final int step) {
        return Character.toString(index - step);
    }

    public int calculateFileDistance(final SquareCoordinate other) {
        return other.fileIndex - this.fileIndex;
    }

    public int calculateRankDistance(final SquareCoordinate other) {
        return other.rankIndex - this.rankIndex;
    }

    public SquareCoordinate horizontalMove(final int step) {
        return SquareCoordinate.of(addIndex(fileIndex, step) + rankIndex);
    }

    public SquareCoordinate verticalMove(final int step) {
        return SquareCoordinate.of(fileIndex + addIndex(rankIndex, step));
    }

    public SquareCoordinate positiveDiagonalMove(final int step) {
        return SquareCoordinate.of(addIndex(fileIndex, step) + addIndex(rankIndex, step));
    }

    public SquareCoordinate negativeDiagonalMove(final int step) {
        return SquareCoordinate.of(subtractIndex(fileIndex, step) + addIndex(rankIndex, step));
    }
}
