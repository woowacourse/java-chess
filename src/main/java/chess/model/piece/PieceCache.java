package chess.model.piece;

import static chess.model.PieceColor.*;

import java.util.HashMap;
import java.util.Map;

public class PieceCache {
    private static final Map<String, Piece> CACHE = new HashMap<>();

    static {
        CACHE.put("p", Pawn.colorOf(WHITE));
        CACHE.put("P", Pawn.colorOf(BLACK));

        CACHE.put("b", Bishop.colorOf(WHITE));
        CACHE.put("B", Bishop.colorOf(BLACK));

        CACHE.put("r", Rook.colorOf(WHITE));
        CACHE.put("R", Rook.colorOf(BLACK));

        CACHE.put("k", King.colorOf(WHITE));
        CACHE.put("K", King.colorOf(BLACK));

        CACHE.put("q", Queen.colorOf(WHITE));
        CACHE.put("Q", Queen.colorOf(BLACK));

        CACHE.put("n", Knight.colorOf(WHITE));
        CACHE.put("N", Knight.colorOf(BLACK));
    }

    public static Piece of(String emblem) {
        return CACHE.get(emblem);
    }
}
