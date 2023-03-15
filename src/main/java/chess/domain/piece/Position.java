package chess.domain.piece;

import java.util.Objects;

public final class Position {
    private final int rank;
    private final int file;

    public Position(final int rank, final int file) {
        this.rank = rank;
        this.file = file;
    }

    public Position calculate(final int rankMove, final int fileMove) {
        return new Position(this.rank + rankMove, this.file + fileMove);
    }

    public static Position empty() {
        return new Position(-1, -1);
    }

    public boolean isOverBoard(final int boardSize) {
        return rank < 0 || rank >= boardSize || file < 0 || file >= boardSize;
    }

    public boolean isEmpty() {
        return this.equals(empty());
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
}
