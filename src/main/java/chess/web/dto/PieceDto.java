package chess.web.dto;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Locale;

public class PieceDto {

    private final String pieceType;
    private final String position;
    private final String color;

    public PieceDto(Piece piece, Position position, Color color) {
        this.pieceType = piece.getNotation().toLowerCase();
        this.position = position.getNotation().toLowerCase();
        this.color = color.toString().toLowerCase();
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
