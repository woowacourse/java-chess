package chess.model.board;

import chess.model.piece.Piece;

public class Square {

    private final Position position;
    private final Piece piece;

    public Square(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }
}
