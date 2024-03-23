package chess.domain.piece;

import chess.domain.square.Square;

public abstract class Piece {
    private final PieceColor color;
    private final Square square;

    public Piece(PieceColor color, Square square) {
        this.color = color;
        this.square = square;
    }

    public abstract void move(Square target);

    public abstract PieceType getType();

    public boolean isLocated(Square other) {
        return square.equals(other);
    }

    public PieceColor getColor() {
        return color;
    }

    public Square getSquare() {
        return square;
    }
}
