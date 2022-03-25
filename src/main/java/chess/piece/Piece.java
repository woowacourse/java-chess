package chess.piece;

import chess.position.Transition;
import java.util.Objects;

public abstract class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isPawn() {
        return false;
    }

    public Color getColor() {
        return color;
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
        return Objects.hash(color, getClass());
    }

    @Override
    public String toString() {
        return "Piece{" +
            "color=" + color +
            ", type=" + getClass().getSimpleName();
    }

    public abstract boolean isMovablePosition(Transition to);
}
