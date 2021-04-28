package chess.service.dto;

import chess.domain.piece.Piece;

public class TileDto {
    private final String position;
    private final String piece;

    public TileDto(final Piece piece) {
        this.position = piece.getPosition().toString();
        this.piece = piece.getPiece();
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
