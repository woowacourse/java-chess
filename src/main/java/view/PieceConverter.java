package view;

import domain.piece.Piece;
import domain.piece.PieceType;
import java.util.Map;

public class PieceConverter {

    private final static Map<PieceType, String> converter = Map.of(
        PieceType.BISHOP, "b", PieceType.KING, "k", PieceType.KNIGHT, "n",
        PieceType.PAWN, "p", PieceType.QUEEN, "q", PieceType.ROOK, "r", PieceType.BLANK, ".");

    public static String of(Piece piece) {
        String result = converter.get(piece.getPieceType());
        if (piece.isNotBlank() && piece.isBlack()) {
            return result.toUpperCase();
        }
        return result;
    }
}
