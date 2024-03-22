package chess.domain.piece;

import java.util.Objects;
import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public abstract class Piece {

    private final Color color;
    private final Position position;

    public Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    public boolean isAllyOf(final Piece other) {
        return color == other.color;
    }

    public Color color() {
        return color;
    }

    public Position position() {
        return position;
    }

    public abstract Set<Position> move(final Position source, final Position target);

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return object instanceof Piece other
                && color == other.color
                && position == other.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
