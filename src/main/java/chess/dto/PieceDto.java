package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public class PieceDto {
    private String position;
    private final String color;
    private final String type;

    public PieceDto(String position, String color, String type) {
        this.position = position;
        this.color = color;
        this.type = type;
    }

    public static Map.Entry<Position, Piece> toPieceEntry(PieceDto pieceDto) {
        PieceType type = PieceType.find(pieceDto.getType());
        PieceColor color = PieceColor.find(pieceDto.getColor());
        return Map.entry(Position.of(pieceDto.position), PieceFactory.find(type, color));
    }

    public static PieceDto from(Position position, Piece piece) {
        return new PieceDto(position.getName(), piece.getColor().getName(), piece.getType().getName());
    }

    public static PieceDto of(String position, String color, String type) {
        return new PieceDto(position, color, type);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(position, pieceDto.position) && Objects.equals(color, pieceDto.color) && Objects.equals(type, pieceDto.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color, type);
    }
}
