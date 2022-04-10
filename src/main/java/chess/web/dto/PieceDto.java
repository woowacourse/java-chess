package chess.web.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceDto {

    private String position;
    private String color;
    private String role;

    public PieceDto(String position, String color, String symbol) {
        this.position = position.toLowerCase();
        this.color = color.toLowerCase();
        this.role = symbol.toLowerCase();
    }

    public static PieceDto from(Position position, Piece piece) {
        return new PieceDto(position.ColumnRowString(), piece.getColor().name(), piece.symbol());
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
