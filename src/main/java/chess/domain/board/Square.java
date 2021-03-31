package chess.domain.board;

import chess.domain.board.piece.Piece;

public class Square {
    private final Position position;
    private final Piece piece;

    public Square(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }
}
