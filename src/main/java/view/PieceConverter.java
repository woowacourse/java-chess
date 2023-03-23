package view;

import domain.piece.sliding.Bishop;
import domain.piece.Blank;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import java.util.Map;

public class PieceConverter {

    private final static Map<Class, String> converter = Map.of(
        Bishop.class, "b", King.class, "k", Knight.class, "n",
        Pawn.class, "p", Queen.class, "q", Rook.class, "r", Blank.class, ".");

    public static String of(Piece piece) {
        String result = converter.get(piece.getClass());
        if (piece.isNotBlank() && piece.isBlack()) {
            return result.toUpperCase();
        }
        return result;
    }
}
