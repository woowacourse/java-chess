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
            throw new IllegalArgumentException(
                    String.format("Rank 의 범위는 %s 부터 %s 까지입니다.", LOWER_BOUNDARY_EXCLUDE, UPPER_BOUNDARY_EXCLUDE)
            );
        }
    }

    public static Rank from(final int rank) {
        return new Rank(rank);
    }

    public int interval(final Rank rank) {
        return rank.rank - this.rank;
    }

    public Rank plus(final int amount) {
        return Rank.from(rank + amount);
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
