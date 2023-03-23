package chess.domain.position;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Position {
    private static final int POSITION_COUNT = 64;
    private static final Map<String, Position> cache = new ConcurrentHashMap<>(POSITION_COUNT);
    private final int rank;
    private final int file;

    private Position(final int rank, final int file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(final int rank, final int file) {
        return cache.computeIfAbsent(toKey(rank, file), ignored -> new Position(rank, file));
    }

    public static String toKey(final Integer rank, final Integer file) {
        return rank.toString() + file.toString();
    }

    public static Position copy(final Position other) {
        return Position.of(other.rank, other.file);
    }

    public Position calculate(final int rankMove, final int fileMove) {
        return new Position(this.rank + rankMove, this.file + fileMove);
    }

    public boolean isOver(final int limit) {
        return rank < 0 || rank >= limit || file < 0 || file >= limit;
    }

    public boolean isRankGreaterThan(final int rank) {
        return this.rank > rank;
    }

    public Position computeUnitPosition(final Position target) {
        int fileGap = target.file - this.file;
        int rankGap = target.rank - this.rank;
        int gcdGap = getGcdGap(Math.abs(fileGap), Math.abs(rankGap));
        return Position.of(rankGap / gcdGap, fileGap / gcdGap);
    }

    private int getGcdGap(int fileGap, int rankGap) {
        if (fileGap == 0) {
            return rankGap;
        }
        if (rankGap == 0) {
            return fileGap;
        }
        return getGcdGap(fileGap, fileGap % rankGap);
    }

    public boolean isUpsideDiagonalPosition(Position target) {
        int fileGap = target.file - this.file;
        int rankGap = target.rank - this.rank;
        return Math.abs(fileGap) == 1 && rankGap == 1;
    }

    public boolean isDownSideDiagonalPosition(Position target) {
        int fileGap = target.file - this.file;
        int rankGap = target.rank - this.rank;
        return Math.abs(fileGap) == 1 && rankGap == -1;

    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return "Position{" +
                "rank=" + rank +
                ", file=" + file +
                '}';
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
