package chess.web.dto;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceDto {

    private final String pieceType;
    private final String position;
    private final String color;

    public PieceDto(Piece piece, Position position, Color color) {
        this(piece.getNotation(), position.getNotation(), color.getName());
    }

    public PieceDto(String pieceType, String position, String color) {
        this.pieceType = pieceType;
        this.position = position;
        this.color = color;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }
}
