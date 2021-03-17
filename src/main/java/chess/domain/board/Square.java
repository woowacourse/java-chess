package chess.domain.board;

import chess.domain.board.piece.Empty;
import chess.domain.board.piece.Owner;
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

        piece.validateMove(this, target);

        target.piece = this.piece;
        this.piece = Empty.getInstance();
    }

    @Override
    public String toString() {
        return piece.makeSymbol();
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isStraight(Square target) {
        return this.horizontal.equals(target.horizontal) || this.vertical.equals(target.vertical);
    }

    public boolean isDiagonal(Square target) {
        return this.horizontal.getDistance(target.horizontal)
                == this.vertical.getDistance(target.vertical);
    }

    public int getDistance(Square target){
        if(isStraight(target)){
            return this.horizontal.getDistance(target.horizontal)
                    + this.vertical.getDistance(target.vertical);
        }

        if(isDiagonal(target)){
            return this.horizontal.getDistance(target.horizontal);
        }

        throw new IllegalArgumentException();
    }

    public Vertical getVertical() {
        return vertical;
    }

    public Horizontal getHorizontal() {
        return horizontal;
    }

    public boolean isOn(Horizontal horizontal) {
        return this.horizontal.equals(horizontal);
    }

    public boolean hasEmpty(Square target) {
        return !(target.piece instanceof Empty);
    }

    public boolean isEnemy(Square target) {
        return this.piece.isEnemy(target.piece);
    }

    public boolean isForward(Owner owner, Square target) {
        return this.horizontal.isForward(owner, target.getHorizontal());
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
}
