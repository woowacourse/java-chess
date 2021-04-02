package chess.dto;

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

    @Override
    public String toString() {
        return "PieceDto{" +
                "notation='" + notation + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
