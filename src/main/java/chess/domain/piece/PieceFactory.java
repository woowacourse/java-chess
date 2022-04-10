package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public final class PieceFactory {
    private static final Map<String, Piece> CACHE = new HashMap<>();

    private PieceFactory() {
    }

    public static Piece getInstance(String pieceName) {
        return CACHE.computeIfAbsent(pieceName.trim().toLowerCase(), Symbol::createPieceByName);
    }
}
