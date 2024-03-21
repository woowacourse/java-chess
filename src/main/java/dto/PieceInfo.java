package dto;

import domain.piece.Piece;
import domain.position.Position;

public record PieceInfo(int fileIndex, int rankIndex, String name) {
    public static PieceInfo of(final Position position, final Piece piece) {
        return new PieceInfo(position.indexOfFile(), position.indexOfRank(), piece.name());
    }
}
