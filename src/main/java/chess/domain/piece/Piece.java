package chess.domain.piece;

import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    protected final Position position;

    public Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public abstract boolean canMoveTo(final Position target);

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    public boolean isPosition(final Position other) {
        return this.position.equals(other);
    }
}
