package chess.domain;

import java.util.Objects;

public class Rank {

    private final int rank;

    public Rank(int rank) {
        validate(rank);
        this.rank = rank;
    }

    public static Rank from(int rank) {
        return new Rank(rank);
    }

    private void validate(int rank) {
        if (rank < 0 || 7 < rank) {
            throw new IllegalArgumentException("기물의 가로 위치는 최소 0부터 최대 7까지 놓을 수 있습니다.");
        }
    }

    public int calculateDistance(int rank) {
        return this.rank - rank;
    }

    public int move(int rankDirection) {
        return this.rank + rankDirection;
    }

    public int getRank() {
        return this.rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank1 = (Rank) o;
        return rank == rank1.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank);
    }

    @Override
    public String toString() {
        return "Rank{" +
                "rank=" + rank +
                '}';
    }

}
