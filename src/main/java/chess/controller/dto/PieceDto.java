package chess.controller.dto;

import chess.domain.chess.CampType;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.PieceType;

public final class PieceDto {
    private final PieceType pieceType;
    private final CampType campType;

    private PieceDto(final PieceType pieceType, final CampType campType) {
        this.pieceType = pieceType;
        this.campType = campType;
    }

    public static PieceDto from(final Piece piece) {
        return new PieceDto(piece.getPieceType(), piece.getCampType());
    }

    public boolean isSameCamp(final CampType campType) {
        return this.campType == campType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
