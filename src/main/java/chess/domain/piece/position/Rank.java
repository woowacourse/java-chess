package chess.domain.piece.position;

import java.util.Objects;

public class Rank {

    public static final int MAX = 8;
    public static final int MIN = 1;

    private final int value;

    private Rank(final int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException(
                    String.format("Rank 의 범위는 %s 부터 %s 까지입니다.", MIN, MAX)
            );
        }
    }

    public static Rank from(final int rank) {
        return new Rank(rank);
    }

    public int interval(final Rank rank) {
        return rank.value - this.value;
    }

    public Rank plus(final int amount) {
        return Rank.from(value + amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Rank)) return false;
        final Rank rank1 = (Rank) o;
        return value == rank1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int value() {
        return value;
    }
}
