package dto;

import domain.piece.PieceType;
import domain.piece.Piece;
import domain.piece.PieceColor;

public record PieceDto(PieceType type, PieceColor color) {

    public static PieceDto from(final Piece piece) {
        return new PieceDto(piece.pieceType(), piece.pieceColor());
    }
}
