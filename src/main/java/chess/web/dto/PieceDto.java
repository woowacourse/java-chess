package chess.dto;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceDto {

    private String position;
    private String color;
    private String role;

    private PieceDto(Position position, Color color, String symbol) {
        this.position = position.toString().toLowerCase();
        this.color = color.name().toLowerCase();
        this.role = symbol.toLowerCase();
    }

    public static PieceDto from(Position position, Piece piece) {
        return new PieceDto(position, piece.getColor(), piece.symbol());
    }

    public String getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }

    public String getRole() {
        return role;
    }
}
