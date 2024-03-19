package domain;

import domain.piece.Piece;
import java.util.Objects;

public class Square {

    private final Position position;
    private final Piece piece;

    public Square(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return Objects.equals(position, square.position) && Objects.equals(piece, square.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, piece);
    }

    @Override
    public String toString() {
        return "Square{" +
            "position=" + position +
            ", piece=" + piece +
            '}';
    }
}
