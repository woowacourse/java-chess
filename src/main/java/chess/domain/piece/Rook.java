package chess.domain.piece;

import util.NullChecker;

import java.util.HashMap;
import java.util.Map;

public class Rook extends RepeatPiece {
    private final static Map<Color, Piece> CACHE = new HashMap<>();
    private final static Type type = Type.ROOK;

    static {
        for (Color color : Color.values()) {
            CACHE.put(color, new Rook(color, type));
        }
    }

    public Rook(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }
}

