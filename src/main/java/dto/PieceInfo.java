package dto;

import domain.Position;
import domain.piece.Piece;

public record PieceInfo(int rankIndex, int fileIndex, String name) {
    public static PieceInfo of(final Position position, final Piece piece) {
        return new PieceInfo(position.indexOfRank(), position.indexOfFile(), piece.name());
    }
}
