package chess.dto;

import chess.domain.piece.Piece;

public record PieceDto(PieceType type, boolean isBlack) {

    public static PieceDto from(Piece piece) {
        PieceType type = PieceType.from(piece);
        boolean isBlackTeam = piece.isBlackTeam();

        return new PieceDto(type, isBlackTeam);
    }
}
