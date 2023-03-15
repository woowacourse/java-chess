package domain.piece;

import domain.Color;
import domain.Location;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    abstract public List<Location> explore(Location start, Location end);

    public boolean isSameColor(Piece piece) {
        return this.color.equals(piece.color);
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
}
