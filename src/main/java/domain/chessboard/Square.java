package domain.chessboard;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.piece.Piece;

public class Square {

    private static final Empty EMPTY_STATUS = new Empty(EmptyType.EMPTY);

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

    public Route findRoute(final Position source, final Position target) {
        return squareStatus.findRoute(source, target);
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public Color getColor() {
        return squareStatus.getColor();
    }

    public Type getType() {
        return squareStatus.getType();
    }
}
