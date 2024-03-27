package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public record PieceInfo(int fileIndex, int rankIndex, String pieceType, String pieceColor) {
    public static PieceInfo of(final Position position, final Piece piece) {
        return new PieceInfo(position.indexOfFile(), position.indexOfRank(), piece.type().name(), piece.color().name());
    }
}
