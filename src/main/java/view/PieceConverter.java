package view;

import domain.piece.Piece;
import domain.piece.PieceInfo;
import java.util.Map;

public class PieceConverter {

    private final static Map<PieceInfo, String> converter = Map.of(
        PieceInfo.BISHOP, "b", PieceInfo.KING, "k", PieceInfo.KNIGHT, "n",
        PieceInfo.PAWN, "p", PieceInfo.QUEEN, "q", PieceInfo.ROOK, "r", PieceInfo.BLANK, ".");

    public static String of(Piece piece) {
        String result = converter.get(piece.getPieceType());
        if (piece.isNotBlank() && piece.isBlack()) {
            return result.toUpperCase();
        }
        return result;
    }
}
