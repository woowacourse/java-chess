package chess.model.board;

import chess.model.piece.Piece;

public class Square {
    private final Piece piece;

    public Square(Piece piece) {
        this.piece = piece;
    }

    public String getPieceSignature() {
        return piece.getSignature();
    }
}
