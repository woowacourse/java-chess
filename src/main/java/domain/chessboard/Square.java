package domain.chessboard;

import domain.piece.Piece;

public class Square {

    private static final Empty EMPTY_STATUS = new Empty();

    private SquareStatus squareStatus;

    public Square(final SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }

    public void beEmpty() {
        squareStatus = EMPTY_STATUS;
    }

    public void bePiece(final Piece piece) {
        squareStatus = piece;
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }


}
