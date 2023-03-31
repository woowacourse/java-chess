package chess.model.position;

import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final File file, final Rank rank) {
        return Positions.getInstance(file, rank);
    }

    public Distance differ(final Position other) {
        final int differRank = this.rank.differ(other.rank);
        final int differFile = this.file.differ(other.file);

        return new Distance(differFile, differRank);
    }

    public Position next(final Direction direction) {
        final File nextFile = file.next(direction.file());
        final Rank nextRank = rank.next(direction.rank());
        return Positions.getInstance(nextFile, nextRank);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return file.name() + rank.value();
    }

    public String getPosition() {
        return file.name() + rank.value();
    }
}
