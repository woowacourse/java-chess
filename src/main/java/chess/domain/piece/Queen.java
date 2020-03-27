package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class Queen extends RepeatMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();

    private final static Type type = Type.QUEEN;

    static {
        for (Color color : Color.values()) {
            CACHE.put(color, new Queen(color, type));
        }
    }

    public Queen(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }
}
