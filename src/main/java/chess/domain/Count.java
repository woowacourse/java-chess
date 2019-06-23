package chess.domain;

import java.util.Objects;

import chess.domain.piece.AbstractPiece;

public class Count {
    private static final int COUNT_UNIT = 1;
    private int count;

    public Count() {
        this.count = 0;
    }

    public Count(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Count add() {
        count += COUNT_UNIT;
        return this;
    }

    public double score(final AbstractPiece abstractPiece) {
        return abstractPiece.score(count);
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

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
