package chess.controller.web.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private String display;

    public PieceDto(Piece piece) {
        this.display = piece.display();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
