package chess.service.dto;

import chess.domain.piece.Piece;

public class TileDto {
    private final String position;
    private final String piece;

    public TileDto(final String position, final String piece) {
        this.position = position;
        this.piece = piece;
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
