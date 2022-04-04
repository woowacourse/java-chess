package web.dto;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.Position;
import java.util.Objects;

public class PieceDto {

    private final Position position;
    private final Color color;
    private final PieceType type;

    public PieceDto(Position position, PieceType type, Color color) {
        this.position = position;
        this.type = type;
        this.color = color;
    }

    public PieceDto(Position position, Piece piece) {
        this.position = position;
        this.color = piece.getColor();
        this.type = PieceType.valueOf(piece);
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public Piece createPiece() {
        return type.createPiece(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(position, pieceDto.position) && color == pieceDto.color && type == pieceDto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color, type);
    }
}
