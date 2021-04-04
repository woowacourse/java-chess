package chess.dto;

import chess.domain.piece.Piece;

public class PieceDTO {
    private final String color;
    private final String type;
    private final String position;

    public PieceDTO(Piece piece) {
        this.color = piece.getColor();
        this.type = piece.getType();
        this.position = piece.getPosition();
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getPosition() {
        return position;
    }
}
