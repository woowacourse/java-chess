package domain.chessboard;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.PieceType;

public class Square {

    private static final Empty EMPTY_STATUS = new Empty();

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

    public Route findRoute(MovePosition movePosition) {
        return squareStatus.findRoute(movePosition);
    }

    public boolean isSameColor(final Color color) {
        return squareStatus.getColor() == color;
    }
    
    public boolean isSameType(Type type) {
        return squareStatus.getType() == type;
    }

    public boolean isNotSameType(Type type) {
        return squareStatus.getType() != type;
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    public Type getType() {
        return squareStatus.getType();
    }

}
