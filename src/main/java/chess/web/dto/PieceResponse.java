package chess.web.dto;

import chess.domain.piece.Piece;

public class PieceResponse {

    private final String notation;
    private final String color;

    public PieceResponse(Piece piece) {
        this.notation = piece.getNotation();
        this.color = piece.getColor();
    }

    public String getNotation() {
        return notation;
    }

    public String getColor() {
        return color;
    }

    public PieceResponse(String notation, String color) {
        this.notation = notation;
        this.color = color;
    }
}
