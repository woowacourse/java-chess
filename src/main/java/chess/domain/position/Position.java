package chess.domain.position;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Position {
    private static final Map<String, Position> CACHE = new ConcurrentHashMap<>();

    private final ChessFile file;
    private final ChessRank rank;

    private Position(final ChessFile file, final ChessRank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final String position) {
        return CACHE.computeIfAbsent(position, key -> new Position(
                ChessFile.findByValue(String.valueOf(position.charAt(0))),
                ChessRank.findByValue(String.valueOf(position.charAt(1)))
        ));
    }

    public static Position of(final ChessFile file, final ChessRank rank) {
        return CACHE.computeIfAbsent(toKey(file, rank), key -> new Position(file, rank));
    }

    private static String toKey(final ChessFile file, final ChessRank rank) {
        return file.value() + rank.value();
    }

    public boolean isRank(final ChessRank rank) {
        return this.rank == rank;
    }

    public int calculateDistanceTo(final Position target) {
        int fileDistance = Math.abs(calculateFileDifferenceTo(target));
        int rankDistance = Math.abs(calculateRankDifferenceTo(target));
        if (fileDistance > 0) {
            return fileDistance;
        }

        return rankDistance;
    }

    public int calculateFileDifferenceTo(final Position target) {
        return target.file.index() - file.index();
    }

    public int calculateRankDifferenceTo(final Position target) {
        return target.rank.index() - rank.index();
    }

    public int indexOfFile() {
        return file.index();
    }

    public int indexOfRank() {
        return rank.index();
    }

    public Position move(final Direction direction) {
        return Position.of(this.file.move(direction), this.rank.move(direction));
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }
}
