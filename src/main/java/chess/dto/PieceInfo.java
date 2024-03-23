package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public record PieceInfo(int fileIndex, int rankIndex, PieceType type) {
    public static PieceInfo of(final Position position, final Piece piece) {
        return new PieceInfo(position.indexOfFile(), position.indexOfRank(), piece.type());
    }
}
