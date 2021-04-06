package chess.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class PieceDto {
    private final String notation;
    private final String color;

    private PieceDto(String notation, String color) {
        this.notation = notation;
        this.color = color;
    }

    public static PieceDto from(Piece piece){
        return new PieceDto(piece.getName(), piece.getColor().name());
    }

    public Piece toPiece() {
        return PieceDeserializeTable.deserializeFrom(this.notation, Color.of(this.color));
    }

    @Override
    public String toString() {
        return "PieceDto{" +
                "notation='" + notation + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
