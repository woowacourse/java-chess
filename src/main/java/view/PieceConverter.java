package view;

import java.util.Map;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Rook;
import domain.piece.Queen;

public class PieceConverter {

    private final static Map<Class, String> converter = Map.of(
        Bishop.class, "b", King.class, "k", Knight.class, "n",
        Pawn.class, "p", Queen.class, "q", Rook.class, "r");

    public static String of(Piece piece) {
        if (piece == null) {
            return ".";
        }
        String result = converter.get(piece.getClass());
        if (piece.isBlack()) {
            return result.toUpperCase();
        }
        return result;
    }
}
