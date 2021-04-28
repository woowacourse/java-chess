package chess.service.dto;

import chess.domain.piece.Piece;

public class PieceStatusDto {
    private final String position;
    private final String pieceName;

    public PieceStatusDto(final Piece piece) {
        this.position = piece.getPosition().toString();
        this.pieceName = piece.getPiece();
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getPosition() {
        return position;
    }
}
