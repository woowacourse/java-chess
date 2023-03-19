package domain.board;

import domain.Location;
import domain.piece.EmptyPiece;
import domain.piece.Piece;
import java.util.List;
import java.util.Objects;

public final class Square {

    private Piece piece;

    public Square(final Piece piece) {
        this.piece = piece;
    }

    public static Square empty() {
        return new Square(EmptyPiece.create());
    }

    public List<Location> searchPath(final Location start, final Location end) {
        return piece.searchPath(start, end);
    }

    public void moveTo(final Square square) {
        square.piece = this.piece;
        this.piece = EmptyPiece.create();
    }

    public boolean isWhite() {
        return piece.isWhite();
    }

    public boolean isBlack() {
        return piece.isBlack();
    }

    public boolean isNotEmpty() {
        return isWhite() || isBlack();
    }

    public Piece getPiece() {
        return piece;
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
