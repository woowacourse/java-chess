package chess.model.board;

import chess.model.unit.Piece;

public class Position {
    private final Square square;
    private final Piece piece;

    Position(final Square square, final Piece piece) {
        this.square = square;
        this.piece = piece;
    }

    public Square getSquare() {
        return square;
    }

    public Piece getPiece() {
        return piece;
    }
}
