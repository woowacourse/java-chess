package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class Knight extends OneTimeMultipleMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            CACHE.put(color, new Knight(color, Type.KNIGHT));
        }
    }

    public Knight(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }

}
