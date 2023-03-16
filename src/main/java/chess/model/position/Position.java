package chess.model.position;

import java.util.Objects;

public class Position implements IndexConvertable {

    private final File file;
    private final Rank rank;

    public Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Distance differ(final Position other) {
        final int differRank = this.rank.differ(other.rank);
        final int differFile = this.file.differ(other.file);

        return new Distance(differFile, differRank);
    }

    @Override
    public int convertToIndex() {
        return ((rank.value() - 1) * FILE_MAX_SIZE) + (file.value() - 1);
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
}
