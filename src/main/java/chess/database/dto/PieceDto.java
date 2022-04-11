package chess.database.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class PieceDto {

    private final String type;
    private final String color;

    private PieceDto(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public static PieceDto of(Piece piece) {
        return new PieceDto(
            piece.getType(),
            piece.getColor()
        );
    }

    public static PieceDto of(String type, String color) {
        return new PieceDto(type, color);
    }

    public boolean isEmpty() {
        return this.type.equals(PieceType.EMPTY.name());
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
