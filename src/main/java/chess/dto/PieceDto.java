package chess.dto;

import chess.piece.Piece;
import chess.piece.detail.Color;

public class PieceDto {
    private final String name;

    public PieceDto(final String name) {
        this.name = name;
    }

    public static PieceDto toDto(final Piece piece) {
        String name = "";
        if (piece.getColor() == Color.BLACK) {
            name = piece.getName().getName().toUpperCase();
        }

        if (piece.getColor() == Color.WHITE) {
            name = piece.getName().getName().toLowerCase();
        }

        return new PieceDto(name);
    }

    public static PieceDto toEmptyDto() {
        return new PieceDto(".");
    }

    public String getName() {
        return name;
    }
}
