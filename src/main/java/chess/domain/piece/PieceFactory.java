package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    public static Map<String, Piece> CACHE = new HashMap<>();

    static {
        CACHE.putIfAbsent("p", Pawn.of(Color.WHITE));
        CACHE.putIfAbsent("r", Rook.of(Color.WHITE));
        CACHE.putIfAbsent("n", Knight.of(Color.WHITE));
        CACHE.putIfAbsent("b", Bishop.of(Color.WHITE));
        CACHE.putIfAbsent("q", Queen.of(Color.WHITE));
        CACHE.putIfAbsent("k", King.of(Color.WHITE));

        CACHE.putIfAbsent("P", Pawn.of(Color.BLACK));
        CACHE.putIfAbsent("R", Rook.of(Color.BLACK));
        CACHE.putIfAbsent("N", Knight.of(Color.BLACK));
        CACHE.putIfAbsent("B", Bishop.of(Color.BLACK));
        CACHE.putIfAbsent("Q", Queen.of(Color.BLACK));
        CACHE.putIfAbsent("K", King.of(Color.BLACK));
    }

    public static Piece of(String pieceName) {
        return CACHE.get(pieceName);
    }
}
