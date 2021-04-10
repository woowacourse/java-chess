package chess.dto;

import chess.domain.piece.Piece;

public final class PieceDto {

    private final String type;
    private final String color;

    public PieceDto(final Piece piece) {
        this.type = piece.type();
        this.color = piece.colorName();
    }

    public String type() {
        return type;
    }

    public String color() {
        return color;
    }
}
