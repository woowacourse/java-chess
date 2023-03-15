package domain;

import domain.piece.Piece;
import java.util.List;
import java.util.Objects;

public class Square {

    private Piece piece;

    public Square(final Piece piece) {
        this.piece = piece;
    }

    public static Square empty() {
        return new Square(null);
    }

    public List<Location> searchPath(Location start, Location end) {
        return piece.searchPath(start, end);
    }

    public boolean isNotNull() {
        return piece != null;
    }

    public boolean haveSameColor(final Square startSquare) {
        return piece.isSameColor(startSquare.piece);
    }

    public void moveTo(final Square square) {
        square.piece = this.piece;
        this.piece = null;
    }

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

    public Piece getPiece() {
        return piece;
    }
}
