package chess.domain.piece;

import chess.domain.position.Movement;
import chess.domain.position.Position;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public abstract Set<Position> getRoute(final Movement movement);

    public boolean isOpponent(final Piece other) {
        return this.color != other.color;
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
