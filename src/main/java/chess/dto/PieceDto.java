package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public record PieceDto(int file, int rank, String type) {

    public static PieceDto from(Position position, PieceType pieceType) {
        int file = position.file();
        int rank = position.rank();
        String type = pieceType.name();
        return new PieceDto(file, rank, type);
    }

    public Position getPosition() {
        return new Position(file, rank);
    }

    public Piece getPiece() {
        return PieceType.valueOf(type).getPiece();
    }
}
