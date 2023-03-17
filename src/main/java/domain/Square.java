package domain;

import domain.piece.EmptyPiece;
import domain.piece.Piece;
import java.util.List;
import java.util.Objects;

public class Square {

    private Piece piece;

    public Square(final Piece piece) {
        this.piece = piece;
    }

    public static Square empty() {
        return new Square(EmptyPiece.make());
    }

    public List<Location> searchPath(Location start, Location end) {
        return piece.searchPath(start, end);
    }

    public boolean isNotEmpty() {
        return !piece.equals(EmptyPiece.make());
    }

    public void moveTo(final Square square) {
        square.piece = this.piece;
        this.piece = EmptyPiece.make();
    }

    public boolean isWhite() {
        return piece.isWhite();
    }

    public boolean isBlack() {
        return piece.isBlack();
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

    public Piece getPiece() {
        return piece;
    }
}
