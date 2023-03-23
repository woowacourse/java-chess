package dto;

import chess.domain.chesspiece.*;

public class PieceDto {
    private final String side;
    private final String type;

    private PieceDto(final String side, final String type) {
        this.side = side;
        this.type = type;
    }

    public static PieceDto from(final Piece piece) {
        return new PieceDto(piece.getSide(), piece.getName());
    }

    public String getSide() {
        return side;
    }

    public String getType() {
        return type;
    }
}
