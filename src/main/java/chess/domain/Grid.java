package chess.domain;

import chess.domain.piece.Piece;

public class Grid {
    private final Piece piece;

    public Grid(Piece piece) {
        this.piece = piece;
    }

    public boolean isBlank(){
        return piece.isBlank();
    }

    public Piece getPiece() {
        return piece;
    }
}
