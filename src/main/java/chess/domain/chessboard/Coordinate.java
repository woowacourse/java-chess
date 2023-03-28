package chess.domain.chessboard;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public final class Coordinate {
    private static final String ALPHANUMERIC_REGEX = "^[a-h][1-8]$";
    private static final int FILE_PARSE_INDEX = 0;
    private static final int RANK_PARSE_INDEX = 1;
    private static final int DIRECTION_CONVERTER = -1;
    private static final Map<String, Coordinate> cache = new HashMap<>();

    private final FileIndex file;
    private final RankIndex rank;

    private Coordinate(final String alphanumeric) {
        validateAlphanumeric(alphanumeric);
        this.file = parsingFileIndex(alphanumeric);
        this.rank = parsingRankIndex(alphanumeric);
    }

    public static Coordinate from(final String alphanumeric) {
        return cache.computeIfAbsent(alphanumeric, key -> new Coordinate(alphanumeric));
    }

    private void validateAlphanumeric(final String alphanumeric) {
        if (!Pattern.matches(ALPHANUMERIC_REGEX, alphanumeric)) {
            throw new IllegalArgumentException("잘못된 좌표값입니다." + alphanumeric);
        }
    }

    private FileIndex parsingFileIndex(final String alphanumeric) {
        return FileIndex.of(alphanumeric.charAt(FILE_PARSE_INDEX));
    }

    private RankIndex parsingRankIndex(final String alphanumeric) {
        return RankIndex.of(alphanumeric.charAt(RANK_PARSE_INDEX));
    }

    public boolean isSameRank(final Coordinate other) {
        return this.rank == other.rank;
    }

    public boolean isSameFile(final Coordinate other) {
        return this.file == other.file;
    }

    public boolean isMyFile(final FileIndex fileIndex) {
        return this.file == fileIndex;
    }

    public boolean isPositiveDiagonal(final Coordinate other) {
        final int rankDistance = other.rank.index - this.rank.index;
        final int fileDistance = other.file.index - this.file.index;

        return rankDistance == fileDistance;
    }

    public boolean isNegativeDiagonal(final Coordinate other) {
        final int rankDistance = other.rank.index - this.rank.index;
        final int fileDistance = other.file.index - this.file.index;

        return rankDistance == (DIRECTION_CONVERTER * fileDistance);
    }

    public boolean isNotDiagonal(final Coordinate other) {
        return (!isPositiveDiagonal(other) && !isNegativeDiagonal(other));
    }

    public boolean isNotOrthogonal(final Coordinate other) {
        return (!isSameRank(other) && !isSameFile(other));
    }

    private String addIndex(final char index, final int step) {
        return Character.toString(index + step);
    }

    private String subtractIndex(final char index, final int step) {
        return Character.toString(index - step);
    }

    public int calculateFileDistance(final Coordinate other) {
        return other.file.index - this.file.index;
    }

    public int calculateRankDistance(final Coordinate other) {
        return other.rank.index - this.rank.index;
    }

    public Coordinate horizontalMove(final int step) {
        return Coordinate.from(addIndex(file.index, step) + rank);
    }

    public Coordinate verticalMove(final int step) {
        return Coordinate.from(file.index + addIndex(rank.index, step));
    }

    public Coordinate positiveDiagonalMove(final int step) {
        return Coordinate.from(addIndex(file.index, step) + addIndex(rank.index, step));
    }

    public Coordinate negativeDiagonalMove(final int step) {
        return Coordinate.from(subtractIndex(file.index, step) + addIndex(rank.index, step));
    }
}
