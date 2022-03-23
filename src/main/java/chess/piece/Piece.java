package chess.piece;

import chess.*;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public abstract void move(Position to);

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
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
        return color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }

    @Override
    public String toString() {
        return "Piece{" +
            "color=" + color +
            ", type=" + getClass().getSimpleName() +
            ", position=" + position +
            '}';
    }
}
