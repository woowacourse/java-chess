package chess.model.domain.piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static final Map<String, Piece> CACHE;

    static {
        Map<String, Piece> cache = new HashMap<>();
        cache.put("BLACK_PAWN", Pawn.getPieceInstance(Color.BLACK));
        cache.put("WHITE_PAWN", Pawn.getPieceInstance(Color.WHITE));
        cache.put("BLACK_ROOK", Rook.getPieceInstance(Color.BLACK));
        cache.put("WHITE_ROOK", Rook.getPieceInstance(Color.WHITE));
        cache.put("BLACK_KNIGHT", Knight.getPieceInstance(Color.BLACK));
        cache.put("WHITE_KNIGHT", Knight.getPieceInstance(Color.WHITE));
        cache.put("BLACK_BISHOP", Bishop.getPieceInstance(Color.BLACK));
        cache.put("WHITE_BISHOP", Bishop.getPieceInstance(Color.WHITE));
        cache.put("BLACK_QUEEN", Queen.getPieceInstance(Color.BLACK));
        cache.put("WHITE_QUEEN", Queen.getPieceInstance(Color.WHITE));
        cache.put("BLACK_KING", King.getPieceInstance(Color.BLACK));
        cache.put("WHITE_KING", King.getPieceInstance(Color.WHITE));

        CACHE = Collections.unmodifiableMap(cache);
    }

    public static Piece of(String pieceName) {
        return CACHE.keySet().stream()
            .filter(name -> name.equalsIgnoreCase(pieceName))
            .map(CACHE::get)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static String getName(Piece piece) {
        return CACHE.keySet().stream()
            .filter(key -> CACHE.get(key) == piece)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
