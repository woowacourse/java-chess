package chess.controller;

import chess.domain.piece.Piece;

public class PieceDTO {
    private String display;

    public PieceDTO(Piece piece) {
        this.display = piece.display();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
