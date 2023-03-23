package chess.domain.position;

import java.util.Objects;

public final class Position {
    private final int rank;
    private final int file;

    public Position(final int rank, final int file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position copy(final Position other) {
        return new Position(other.rank, other.file);
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
        return new Position(rankGap / gcdGap, fileGap / gcdGap);
    }

    private int getGcdGap(final int fileGap, final int rankGap) {
        if (fileGap < rankGap) {
            return computeGcd(fileGap, rankGap);
        }
        return computeGcd(rankGap, fileGap);
    }

    private int computeGcd(int smallPosition, int bigPosition) {
        if (bigPosition == 0)
            return smallPosition;
        return computeGcd(bigPosition, smallPosition % bigPosition);
    }

    public boolean isDiagonalPosition(Position target) {
        int fileGap = target.file - this.file;
        int rankGap = target.rank - this.rank;
        return Math.abs(fileGap * rankGap) == 1;
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
