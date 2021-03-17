package chess.domain.board;

import chess.domain.board.piece.Empty;
import chess.domain.board.piece.Piece;

import java.util.Objects;

public class Square {
    private final Vertical vertical;
    private final Horizontal horizontal;
    private Piece piece;

    public Square(Vertical vertical, Horizontal horizontal, Piece piece) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.piece = piece;
    }

    public Square(Vertical vertical, Horizontal horizontal) {
        this(vertical, horizontal, Empty.getInstance());
    }

    public void move(final Square target){
        if(piece instanceof Empty){
            throw new IllegalArgumentException();
        }

        target.piece = this.piece;
        this.piece = Empty.getInstance();
    }

    @Override
    public String toString() {
        return piece.makeSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return vertical == square.vertical && horizontal == square.horizontal && Objects.equals(piece, square.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal, piece);
    }

    public Piece getPiece() {
        return piece;
    }
}
