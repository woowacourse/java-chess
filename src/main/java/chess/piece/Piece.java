package chess.piece;

import chess.position.Position;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Piece transfer(Position to) {
        return createNewPiece(to);
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

    public Position getPosition() {
        return position;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
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

    protected abstract Piece createNewPiece(Position to);

    public abstract boolean isPossibleMovement(Position to);
}
