package model;

import java.util.Objects;
import piece.Blank;
import piece.Piece;

public class Square {

    private Piece piece;

    public Square(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return piece.toString();
    }

    public boolean isBlank() {
        return piece instanceof Blank;
    }

    public void makeBlank() {
        this.piece = new Blank();
    }

    public void setPiece(Piece piece) {
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
        return Objects.equals(piece, square.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece);
    }
}
