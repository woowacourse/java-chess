package chess.domain.dto;

import chess.domain.Piece;

public class PieceDto {

    private final String type;
    private final String color;

    public PieceDto(Piece piece) {
        this(piece.getPieceType().getName(), piece.getPieceColor().getName());
    }

    public PieceDto(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
