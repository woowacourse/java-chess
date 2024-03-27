package chess.domain.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> POSITION_POOL;
    private static final int KNIGHT_CALCULATE_NUMBER = 2;

    static {
        POSITION_POOL = new HashMap<>();
        for (File file : File.values()) {
            putAllRankPositionToPool(file);
        }
    }

    private static void putAllRankPositionToPool(File file) {
        for (Rank rank : Rank.values()) {
            POSITION_POOL.put(toKey(file, rank), new Position(file, rank));
        }
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    private final File file;

    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return POSITION_POOL.get(toKey(file, rank));
    }

    public Position createPositionByDifferencesOf(int fileDifference, int rankDifference) {
        return Position.of(
                file.createFileByDifferenceOf(fileDifference),
                rank.createRankByDifferenceOf(rankDifference)
        );
    }

    public boolean isOnSameRank(Position other) {
        return rank == other.rank;
    }

    public boolean isOnSameFile(Position other) {
        return file == other.file;
    }

    public boolean isOnPositiveSlopeDiagonal(Position other) {
        int rankDifference = rank.subtract(other.rank);
        int fileDifference = file.subtract(other.file);
        return hasSameAbsoluteDifference(rankDifference, fileDifference) &&
                hasSameSign(rankDifference, fileDifference);
    }

    public boolean isOnNegativeSlopeDiagonal(Position other) {
        int rankDifference = rank.subtract(other.rank);
        int fileDifference = file.subtract(other.file);
        return hasSameAbsoluteDifference(rankDifference, fileDifference) &&
                hasDifferentSign(rankDifference, fileDifference);
    }

    public boolean hasLowerFileThan(Position other) {
        return file.subtract(other.file) < 0;
    }

    public boolean hasHigherFileThan(Position other) {
        return file.subtract(other.file) > 0;
    }

    public boolean hasLowerRankThan(Position other) {
        return rank.subtract(other.rank) < 0;
    }

    public boolean hasHigherRankThan(Position other) {
        return rank.subtract(other.rank) > 0;
    }

    private boolean hasDifferentSign(int rankDifference, int fileDifference) {
        return rankDifference * fileDifference < 0;
    }

    private boolean hasSameSign(int rankDifference, int fileDifference) {
        return rankDifference * fileDifference >= 0;
    }

    private boolean hasSameAbsoluteDifference(int rankDifference, int fileDifference) {
        return Math.abs(rankDifference) == Math.abs(fileDifference);
    }

    public boolean isOnKnightRoute(Position other) {
        return Math.abs(rank.subtract(other.rank) * file.subtract(other.file)) == KNIGHT_CALCULATE_NUMBER;
    }

    public boolean isNotEquals(Position other) {
        return !equals(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
