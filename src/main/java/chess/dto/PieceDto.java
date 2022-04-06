package chess.dto;

import chess.domain.piece.Piece;

public final class PieceDto {

    private final String color;
    private final String name;

    public PieceDto(final Piece piece) {
        this.color = piece.getColor().name().toLowerCase();
        this.name = piece.getNotation().name().toLowerCase();
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
