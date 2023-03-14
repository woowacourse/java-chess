package domain;

public class Square {

    private final Piece piece;

    public Square() {
        this.piece = null;
    }

    public Square(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
