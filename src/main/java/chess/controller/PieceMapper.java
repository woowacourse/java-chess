package chess.controller;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.EnumMap;

public class PieceMapper {
    
    private static final EnumMap<PieceType, String> pieceMap = new EnumMap<>(PieceType.class);
    
    static {
        pieceMap.put(PieceType.PAWN, "p");
        pieceMap.put(PieceType.BISHOP, "b");
        pieceMap.put(PieceType.KNIGHT, "n");
        pieceMap.put(PieceType.ROOK, "r");
        pieceMap.put(PieceType.QUEEN, "q");
        pieceMap.put(PieceType.KING, "k");
        pieceMap.put(PieceType.EMPTY, ".");
    }
    
    public static String map(Piece piece) {
        String literalPiece = pieceMap.get(piece.getType());
        if (piece.isBlack()) {
            return literalPiece.toUpperCase();
        }
        return literalPiece;
    }
}
