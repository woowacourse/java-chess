package dto;

import domain.game.Piece;
import domain.position.Position;

public record PieceDto(
        int fileIndex, int rankIndex,
        String color, String type
) {
    public static PieceDto of(Position position, Piece piece) {
        return new PieceDto(
                position.columnIndex(),
                position.rowIndex(),
                piece.color().name(),
                piece.getPieceType().name()
        );
    }
}
