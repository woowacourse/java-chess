package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.square.Square;

public record PieceDrawing(int fileOrdinal, int rankOrdinal, String color, String type) {
    public static PieceDrawing of(Piece piece) {
        Square square = piece.getSquare();
        int fileOrdinal = square.getFileOrdinal();
        int rankOrdinal = square.getRankOrdinal();
        String color = piece.getColor().name();
        String type = piece.getType().name();
        return new PieceDrawing(fileOrdinal, rankOrdinal, color, type);
    }
}
