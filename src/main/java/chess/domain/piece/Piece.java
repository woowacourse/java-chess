package chess.domain.piece;

import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public abstract boolean canMoveTo(final Position source, final Position target);

    public abstract Set<Position> getRoute(final Position source, final Position target);

    public boolean isMySide(final Piece other) {
        return this.color == other.color;
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isClass(final Class<?> other) {
        return getClass().equals(other);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + color;
    }
}
