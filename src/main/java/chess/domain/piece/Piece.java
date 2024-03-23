package chess.domain.piece;

import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected final Color color;
    protected Position position;

    protected Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public abstract boolean canMoveTo(final Position target);

    public abstract Set<Position> getRoute(final Position target);

    public void move(final Position target) {
        this.position = target;
    }

    public boolean isMySide(final Piece other) {
        return this.color == other.color;
    }

    public boolean isPosition(final Position other) {
        return this.position.equals(other);
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isClass(final Class<?> other) {
        return getClass().equals(other);
    }

    public Position getPosition() {
        return position;
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
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + color + "," + position;
    }
}
