package domain.chessboard;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.PieceType;

public class Square {

    private static final Empty EMPTY_STATUS = new Empty(EmptyType.EMPTY);

    private SquareStatus squareStatus;

    public Square(final SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }

    public void beEmpty() {
        squareStatus = EMPTY_STATUS;
    }

    public void bePiece(final Square square) {
        if (square.getType() == PieceType.PAWN) {
            squareStatus = new Pawn(square.squareStatus.getColor());
            return;
        }
        squareStatus = square.squareStatus;
    }

    public Route findRoute(final Position source, final Position target) {
        return squareStatus.findRoute(source, target);
    }

    public boolean isSameColor(final Color color) {
        return squareStatus.getColor() == color;
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public boolean isEqualType(final Type type) {
        return squareStatus.getType() == type;
    }

    public boolean isDifferentType(final Type type) {
        return squareStatus.getType() != type;
    }

    public Type getType() {
        return squareStatus.getType();
    }

}
