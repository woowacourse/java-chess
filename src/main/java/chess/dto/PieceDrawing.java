package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.square.Square;

public record PieceDrawing(int fileOrdinal, int rankOrdinal, String colorName, String typeName) {
    public static PieceDrawing from(Piece piece) {
        Square square = piece.getSquare();
        int fileOrdinal = square.getFileOrdinal();
        int rankOrdinal = square.getRankOrdinal();
        String colorName = piece.getColor().name();
        String typeName = piece.getType().name();
        return new PieceDrawing(fileOrdinal, rankOrdinal, colorName, typeName);
    }
}
