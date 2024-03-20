package dto;

import domain.piece.Piece;
import domain.position.Position;

public record PieceInfo(int rankIndex, int fileIndex, String name) {
    public static PieceInfo of(final Position position, final Piece piece) {
        return new PieceInfo(position.indexOfRank(), position.indexOfFile(), piece.name());
    }
}
