package chess.model.position;

import java.util.Objects;

public class Position {

    private final Rank rank;
    private final File file;

    public Position(final File file, final Rank rank) {
        this.rank = rank;
        this.file = file;
    }

    public Distance differ(final Position other) {
        final int differRank = this.rank.differ(other.rank);
        final int differFile = this.file.differ(other.file);

        return new Distance(differRank, differFile);
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
}
