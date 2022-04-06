package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public class Pieces {

    private static final Map<String, Piece> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color.name().toLowerCase() + "pawn", new Pawn(color));
            CACHE.put(color.name().toLowerCase() + "rook", new Rook(color));
            CACHE.put(color.name().toLowerCase() + "bishop", new Bishop(color));
            CACHE.put(color.name().toLowerCase() + "king", new King(color));
            CACHE.put(color.name().toLowerCase() + "knight", new Knight(color));
            CACHE.put(color.name().toLowerCase() + "queen", new Queen(color));
        }
    }

    private Pieces() {
    }

    public static Piece findPiece(String color, String piece) {
        return CACHE.get(color + piece);
    }
}
