package chess.domain;

import java.util.Objects;

public class Count {
    private int count;

    public Count add() {
        count += 1;
        return this;
    }

    public double score(final AbstractPiece abstractPiece) {
        return count * abstractPiece.getScore();
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
