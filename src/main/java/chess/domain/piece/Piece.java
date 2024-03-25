package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

public abstract class Piece {

    private final PieceColor color;
    protected Square square;

    public Piece(PieceColor color, Square square) {
        this.color = color;
        this.square = square;
    }

    public abstract void move(Board board, Square target);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && square.equals(piece.square);
    }
}
