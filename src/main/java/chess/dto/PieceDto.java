package chess.dto;

import chess.domain.piece.Piece;

public record PieceDto(PieceType type, boolean isBlack) {

    public static PieceDto from(Piece piece) {
        return new PieceDto(PieceType.from(piece), piece.isBlackTeam());
    }
}
