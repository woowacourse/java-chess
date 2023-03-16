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
        validate();
        final Piece piece = (Piece) squareStatus;
        return piece.findRoute(source, target);
    }

    public SquareStatus getSquareStatus() {
        return squareStatus;
    }

    private void validate() {
        if (squareStatus.getClass() == Empty.class) {
            throw new IllegalArgumentException("빈 칸은 선택할 수 없습니다.");
        }
    }

    public Color getColor() {
        validate();
        final Piece piece = (Piece) squareStatus;

        return piece.getColor();
    }

    public Type getType() {
        return squareStatus.getType();
    }
}
