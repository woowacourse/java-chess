package chess.controller;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Map;

public class PieceMapper {
    private static final Map<PieceType, String> mapper = Map.of(
            PieceType.EMPTY, ".",
            PieceType.PAWN, "p",
            PieceType.BISHOP, "b",
            PieceType.KNIGHT, "n",
            PieceType.ROOK, "r",
            PieceType.QUEEN, "q",
            PieceType.KING, "k"
    );
    
    public static String map(Piece piece) {
        if (!piece.isWhite()) {
            return mapper.get(piece.getType()).toUpperCase();
        }
        return mapper.get(piece.getType());
    }
}
