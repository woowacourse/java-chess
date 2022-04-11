package chess.dto;

import java.util.Locale;

import chess.domain.piece.Piece;

public class PieceDTO {
    private final String type;
    private final String color;

    private PieceDTO(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public static PieceDTO of(Piece piece) {
        return new PieceDTO(piece.getType().toUpperCase(Locale.ROOT), piece.getColor().toUpperCase(Locale.ROOT));
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
