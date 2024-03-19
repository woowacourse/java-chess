package chess.model;

import java.util.Objects;

public abstract class Piece implements MoveStrategy {
    protected final Side side;

    protected Piece(Side side) {
        this.side = side;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }
}
