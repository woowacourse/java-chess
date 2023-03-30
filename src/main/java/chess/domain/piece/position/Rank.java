package chess.domain.piece.position;

import java.util.Objects;

public class Rank {

    public static final int MAX = 8;
    public static final int MIN = 1;

    private final int rank;

    public Rank(final int rank) {
        validateRange(rank);
        this.rank = rank;
    }

    private void validateRange(final int rank) {
        if (rank < MIN || rank > MAX) {
            throw new IllegalArgumentException(
                    String.format("Rank 의 범위는 %s 부터 %s 까지입니다.", MIN, MAX)
            );
        }
    }

    public int interval(final Rank rank) {
        return rank.rank - this.rank;
    }

    public Rank plus(final int amount) {
        return new Rank(rank + amount);
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

    public int value() {
        return rank;
    }
}
