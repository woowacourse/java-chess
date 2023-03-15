package domain;

import domain.piece.Piece;
import java.util.Objects;

public class Square {

    private final Piece piece;

    public Square(final Piece piece) {
        this.piece = piece;
    }

    public static Square empty() {
        return new Square(null);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Square square = (Square) o;
        return Objects.equals(piece, square.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece);
    }
}
