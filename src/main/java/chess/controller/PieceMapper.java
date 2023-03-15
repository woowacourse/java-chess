package chess.controller;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class PieceMapper {
    
    public static String map(Piece piece) {
        String defaultPiece = "";
        if (piece.getType() == PieceType.EMPTY) {
            return ".";
        }
        if (piece.getType() == PieceType.PAWN) {
            defaultPiece = "p";
        }
        if (piece.getType() == PieceType.BISHOP) {
            defaultPiece = "b";
        }
        if (piece.getType() == PieceType.KNIGHT) {
            defaultPiece = "n";
        }
        if (piece.getType() == PieceType.ROOK) {
            defaultPiece = "r";
        }
        if (piece.getType() == PieceType.QUEEN) {
            defaultPiece = "q";
        }
        if (piece.getType() == PieceType.KING) {
            defaultPiece = "k";
        }
        if (!piece.isWhite()) {
            return defaultPiece.toUpperCase();
        }
        return defaultPiece;
    }
}
