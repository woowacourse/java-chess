package chess.domain;

import java.util.Objects;

public class Count {
    private static final int COUNT_UNIT = 1;
    private int count;

    public Count() {
    }

    public Count(final int count) {
        this.count = count;
    }

    public Count add() {
        count += COUNT_UNIT;
        return this;
    }

    public double score(final Piece piece) {
        return piece.score(count);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Count count1 = (Count) o;
        return count == count1.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
