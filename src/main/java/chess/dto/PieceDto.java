package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public record PieceDto(int file, int rank, String type) {

    public Position getPosition() {
        return new Position(file, rank);
    }

    public Piece getPiece() {
        return PieceType.valueOf(type).getPiece();
    }
}
