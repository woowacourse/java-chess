package chess.domain.board;

import chess.domain.piece.Piece;

public class Square {
    private Piece piece;

    public Square(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }
}
