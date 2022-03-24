package chess.piece;

import chess.position.Position;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void move(Position to) {
        if (!isMovablePosition(to)) {
            throw new IllegalArgumentException();
        }
        position = to;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameColor(Piece piece) {
        return isSameColor(piece.color);
    }

    public Color getColor() {
        return color;
    }

    protected Position getPosition() {
        return position;
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

    protected abstract boolean isMovablePosition(Position to);
}
