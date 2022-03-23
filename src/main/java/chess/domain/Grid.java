package chess.domain;

import chess.domain.piece.Piece;

public class Grid {
    private final Piece piece;

    public Grid(Piece piece) {
        this.piece = piece;
    }

    public boolean hasPieceOf(Color color){
        return piece.isSameColor(color);
    }

    public Piece getPiece() {
        return piece;
    }
}
