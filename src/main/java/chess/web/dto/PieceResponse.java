package chess.web.dto;

import chess.domain.Color;
import chess.domain.piece.Piece;

public class PieceResponse {

    private final String position;
    private final String notation;
    private final String color;

    public PieceResponse(Piece piece, String position) {
        this.position = position;
        this.notation = piece.getNotation();
        Color color = piece.getColor();
        this.color = color.getValue();
    }

    public String getPosition() {
        return position;
    }

    public String getNotation() {
        return notation;
    }

    public String getColor() {
        return color;
    }
}
