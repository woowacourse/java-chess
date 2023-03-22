package chess.domain.square;

import java.util.Objects;

public final class SquareKey {

    private final File file;
    private final Rank rank;

    public SquareKey(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareKey)) return false;
        SquareKey squareKey = (SquareKey) o;
        return file == squareKey.file && rank == squareKey.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
