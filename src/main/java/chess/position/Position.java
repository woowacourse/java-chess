package chess.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

    private static final Map<String, Position> POSITION_POOL;

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

    public int subtractFile(Position other) {
        return file.subtract(other.file);
    }

    public int subtractRank(Position other) {
        return rank.subtract(other.rank);
    }

    public boolean isNotEquals(Position other) {
        return !this.equals(other);
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
