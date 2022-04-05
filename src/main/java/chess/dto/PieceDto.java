package chess.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;

public class PieceDto {

    private final String type;
    private final String color;

    public PieceDto(final Piece piece) {
        this(piece.getType().getName(), piece.getColor().getName());
    }

    public PieceDto(final String type, final String color) {
        this.type = type;
        this.color = color;
    }

    public Piece toPiece() {
        final Type type = Type.from(this.type);
        final Color color = Color.from(this.color);
        return type.createPiece(color);
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
