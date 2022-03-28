package chess.util;

import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import java.util.Map;

public class PieceToLetterConvertor {
    private static final Map<Class<? extends Piece>, String> letterMap
            = Map.of(King.class, "k", Queen.class, "q",
            Rook.class, "r", Knight.class, "n", Bishop.class, "b",
            Pawn.class, "p", Empty.class, ".");

    public static String convertToLetter(final Piece piece) {
        String pieceDto = letterMap.get(piece.getClass());
        if (piece.isBlack()) {
            return pieceDto.toUpperCase();
        }
        return pieceDto;
    }
}
