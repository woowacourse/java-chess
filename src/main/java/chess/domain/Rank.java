package chess.domain;

import java.util.Objects;

public class Rank {

    private static final int MINIMUM_RANK = 1;
    private static final int MAXIMUM_RANK = 8;

    private final int rank;

    public Rank(int rank) {
        validateRange(rank);
        this.rank = rank;
    }

    private void validateRange(int rank) {
        if (rank < MINIMUM_RANK || rank > MAXIMUM_RANK) {
            throw new IllegalArgumentException(
                    String.format("세로 위치는 %d ~ %d 사이의 값이어야 합니다.", MINIMUM_RANK, MAXIMUM_RANK));
        }
    }

    public int distance(Rank rank) {
        return Math.abs(this.rank - rank.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rank rank1 = (Rank) o;
        return rank == rank1.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank);
    }
}
