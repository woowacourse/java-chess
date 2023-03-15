package chess.domain.piece.position;

import java.util.Objects;

public class Rank {

    private static final int LOWER_BOUNDARY_EXCLUDE = 1;
    private static final int UPPER_BOUNDARY_EXCLUDE = 8;
    private final int rank;

    private Rank(final int rank) {
        validateRange(rank);
        this.rank = rank;
    }

    private void validateRange(final int rank) {
        if (rank < LOWER_BOUNDARY_EXCLUDE || rank > UPPER_BOUNDARY_EXCLUDE) {
            throw new IllegalArgumentException();
        }
    }

    public static Rank from(final int rank) {
        return new Rank(rank);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Rank)) return false;
        final Rank rank1 = (Rank) o;
        return rank == rank1.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank);
    }
}
