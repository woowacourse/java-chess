package chess.model.domain.piece;

import java.util.HashMap;
import java.util.Map;
import util.NullChecker;

public class Queen extends RepeatMovePiece {

    private final static Map<Color, Piece> CACHE = new HashMap<>();

    static {
        CACHE.put(Color.BLACK, new Queen(Color.BLACK, Type.QUEEN));
        CACHE.put(Color.WHITE, new Queen(Color.WHITE, Type.QUEEN));
    }

    public Queen(Color color, Type type) {
        super(color, type);
    }

    public static Piece getPieceInstance(Color color) {
        NullChecker.validateNotNull(color);
        return CACHE.get(color);
    }
}
