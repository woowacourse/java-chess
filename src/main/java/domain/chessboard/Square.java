package domain.chessboard;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.piece.Pawn;
import domain.squarestatus.Empty;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;
import domain.type.PieceType;
import domain.type.Type;

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
        if (square.isEqualType(PieceType.PAWN)) {
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

    public boolean isDifferentColor(final Color color) {
        return squareStatus.getColor() != color;
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

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

}
